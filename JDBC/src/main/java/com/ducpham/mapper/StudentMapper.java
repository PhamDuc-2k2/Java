package com.ducpham.mapper;

import com.ducpham.dto.StudentDTO;
import com.ducpham.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper STUDENT_MAPPER = Mappers.getMapper(StudentMapper.class);

    Student toStudent(StudentDTO studentDTO);

    void updateStudent(StudentDTO studentDTO, @MappingTarget Student student);

    StudentDTO toStudentDTO(Student student);
}
