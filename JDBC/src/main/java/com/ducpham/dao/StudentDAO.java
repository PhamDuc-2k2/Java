package com.ducpham.dao;

import com.ducpham.dto.StudentSearch;
import com.ducpham.enums.EnumGender;
import com.ducpham.exception.AppException;
import com.ducpham.exception.EnumException;
import com.ducpham.model.Student;
import com.ducpham.utils.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO<Student, StudentSearch> {
    private final static Connection CONNECTION = JdbcUtil.getConnection();

    @Override
    public Student getById(Long id) {
        String sql = "select * from tbl_student where id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return convertResultSetToStudent(resultSet);
            throw new AppException(EnumException.NOT_FOUND, "Not found student with id = " + id);
        } catch (SQLException e) {
            throw new AppException(EnumException.SQL_EXCEPTION, "Failed to connect to the database or execute query to get student by id");
        }
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        String sql = "select * from tbl_student";
        try (Connection connection = JdbcUtil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            statement.getUpdateCount();
            while (resultSet.next())
                students.add(convertResultSetToStudent(resultSet));
        } catch (SQLException e) {
            throw new AppException(EnumException.SQL_EXCEPTION, "Failed to connect to the database or execute query to get all students");
        }
        if (students.isEmpty())
            throw new AppException(EnumException.NOT_FOUND, "Not found any students");
        return students;
    }

    @Override
    public List<Student> getAll(StudentSearch search) {
        return List.of();
    }

    @Override
    public Student create(Student request) {
        if (existsByCode(request.getCode()))
            throw new AppException(EnumException.EXISTED, "Student already exists with code " + request.getCode());
        String sql = "insert into tbl_student(code, name, age, gender) values (?, ?, ?, ?)";
        try (
                PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getCode());
            preparedStatement.setString(2, request.getName());
            preparedStatement.setInt(3, request.getAge());
            preparedStatement.setString(4, request.getGender().name());

            preparedStatement.executeQuery();

            CONNECTION.commit();

            return request;
        } catch (SQLException e) {
            try {
                CONNECTION.rollback();
            } catch (SQLException ex) {
                throw new AppException(EnumException.SQL_EXCEPTION, "Failed to rollback transaction");
            }
            throw new AppException(EnumException.SQL_EXCEPTION, "Failed to connect to the database or execute query to create a new student");
        }
    }

    @Override
    public Student update(Long id, Student request) {
        if (existsByCodeAndNotId(id, request.getCode()))
            throw new AppException(EnumException.EXISTED, "Student already exists with code " + request.getCode());
        String sql = "update tbl_student set code = ?, name = ?, age = ?, gender = ? where id = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getCode());
            preparedStatement.setString(2, request.getName());
            preparedStatement.setInt(3, request.getAge());
            preparedStatement.setString(4, request.getGender().name());
            preparedStatement.setLong(5, id);

            int rows = preparedStatement.executeUpdate();
            if (rows != 0) return request;
            throw new AppException(EnumException.BAD_REQUEST, "Failed to update student with id " + request.getId());
        } catch (SQLException e) {
            throw new AppException(EnumException.SQL_EXCEPTION, "Failed to connect to the database or execute query to update student with id " + request.getId());
        }
    }

    @Override
    public String delete(Long id) {
        String sql = "delete from tbl_student where id = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows != 0) return "Deleted student with id = " + id + " successfully";
            throw new AppException(EnumException.BAD_REQUEST, "Failed to delete student with id " + id);
        } catch (SQLException e) {
            throw new AppException(EnumException.SQL_EXCEPTION, "Failed to connect to the database or execute query to delete student with id " + id);

        }

    }

    @Override
    public boolean existsByCode(String code) {
        String sql = "select 1 from tbl_student where code = ? ";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            preparedStatement.setString(1, code);
            return preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByCodeAndNotId(Long id, String code) {
        return false;
    }

    private Student convertResultSetToStudent(ResultSet resultSet) throws SQLException {
        return Student
                .builder()
                .id(resultSet.getLong("id"))
                .code(resultSet.getString("code"))
                .name(resultSet.getString("name"))
                .age(resultSet.getInt("age"))
                .gender(EnumGender.valueOf(resultSet.getString("gender")))
                .build();
    }

}
