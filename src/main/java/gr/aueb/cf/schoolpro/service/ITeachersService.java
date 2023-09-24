package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolpro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;

import java.util.List;

public interface ITeachersService {
    public List<TeacherDTO> getTeacherByLastName(String name) throws NoRecordsFoundException;
    public TeacherDTO getTeacherById(Long id) throws NoRecordsFoundException;
    public TeacherDTO InsertTeacher(TeacherInsertDTO dto) throws InsertUpdateError;
    public TeacherDTO UpdateTeacher(TeacherUpdateDTO dto) throws NoRecordsFoundException;
    public TeacherDTO DeleteTeacher(Long id) throws NoRecordsFoundException;

}
