package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.StudentShowDTO;
import gr.aueb.cf.schoolpro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolpro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;

import java.util.List;

public interface IStudentsService {
    public List<StudentShowDTO> getStudentsByLastName(String name) throws NoRecordsFoundException;
    public StudentShowDTO getStudentById(Long id) throws NoRecordsFoundException;

    public StudentShowDTO insertStudent(StudentInsertDTO dto) throws InsertUpdateError;
    public StudentShowDTO updateStudent(StudentUpdateDTO dto) throws NoRecordsFoundException;

    public StudentShowDTO deleteStudent(Long id) throws NoRecordsFoundException;
}
