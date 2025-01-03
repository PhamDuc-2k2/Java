package com.ducpham.service;

import com.ducpham.dao.StudentDAO;
import com.ducpham.dto.StudentDTO;
import com.ducpham.dto.StudentSearch;
import com.ducpham.mapper.StudentMapper;

import java.util.List;

public class StudentService implements IStudentService<StudentDTO, StudentSearch> {
    private final static StudentDAO STUDENT_DAO = new StudentDAO();
    private final static StudentMapper STUDENT_MAPPER = StudentMapper.STUDENT_MAPPER;

    @Override
    public StudentDTO getById(Long id) {
        return STUDENT_MAPPER.toStudentDTO(STUDENT_DAO.getById(id));
    }

    @Override
    public List<StudentDTO> getAll() {
        return STUDENT_DAO.getAll().stream().map(STUDENT_MAPPER::toStudentDTO).toList();
    }

    @Override
    public List<StudentDTO> getAll(StudentSearch search) {
        return STUDENT_DAO.getAll(search).stream().map(STUDENT_MAPPER::toStudentDTO).toList();
    }

    @Override
    public StudentDTO create(StudentDTO request) {
        return STUDENT_MAPPER.toStudentDTO(STUDENT_DAO.create(STUDENT_MAPPER.toStudent(request)));
    }

    @Override
    public StudentDTO update(Long id, StudentDTO request) {
        return STUDENT_MAPPER.toStudentDTO(STUDENT_DAO.update(id, STUDENT_MAPPER.toStudent(request)));
    }

    @Override
    public String delete(Long id) {
        STUDENT_DAO.delete(id);
        return "Deleted student successfully with id: " + id;
    }


}
