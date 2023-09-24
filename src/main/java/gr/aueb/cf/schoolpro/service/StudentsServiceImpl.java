package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.StudentShowDTO;
import gr.aueb.cf.schoolpro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolpro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolpro.model.Student;
import gr.aueb.cf.schoolpro.repositories.CitiesRepository;
import gr.aueb.cf.schoolpro.repositories.StudentRepository;
import gr.aueb.cf.schoolpro.repositories.UserRepository;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsServiceImpl implements IStudentsService {
    private final StudentRepository repStudents;
    private final UserRepository repUsers;
    private final CitiesRepository repCities;

@Autowired
    public StudentsServiceImpl(StudentRepository repStudents, UserRepository repUsers, CitiesRepository repCities) {
        this.repStudents = repStudents;
        this.repUsers = repUsers;
        this.repCities = repCities;
    }
@Transactional
    @Override
    public List<StudentShowDTO> getStudentsByLastName(String name) throws NoRecordsFoundException {
        List<StudentShowDTO> resultList = new ArrayList<>();
        List<Student> initialList = repStudents.findStudentsByLastNameStartingWith(name);
        if(initialList.isEmpty()) throw new NoRecordsFoundException();
        for(Student student: initialList){
            resultList.add(mapToDTO(student));
        }
        return resultList;
    }
@Transactional
    @Override
    public StudentShowDTO getStudentById(Long id) throws NoRecordsFoundException {
        Student student = repStudents.findStudentById(id);
        if(student == null) throw new NoRecordsFoundException();
        return mapToDTO(student);
    }
@Transactional
    @Override
    public StudentShowDTO insertStudent(StudentInsertDTO dto) throws InsertUpdateError {
        Student student = mapToRep(dto);
        repStudents.save(student);

        if(student.getId() == 0) throw new InsertUpdateError();
        return mapToDTO(student);

    }
@Transactional
    @Override
    public StudentShowDTO updateStudent(StudentUpdateDTO dto) throws NoRecordsFoundException {
        Student initialStudent = repStudents.findStudentById(dto.getId());
        if(initialStudent == null) throw new NoRecordsFoundException();
        Student updatedStudent = mapToRep(dto);
        repStudents.save(updatedStudent);
        return mapToDTO(updatedStudent);


    }
@Transactional
    @Override
    public StudentShowDTO deleteStudent(Long id) throws NoRecordsFoundException {
        Student student = repStudents.findStudentById(id);
        if(student == null) throw new NoRecordsFoundException();
        repStudents.delete(student);
        return mapToDTO(student);

    }

    private StudentShowDTO mapToDTO(Student student){
        StudentShowDTO dto = new StudentShowDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setBirthDate(DateUtil.toString(student.getBirthDate()));
        dto.setGender(student.getGender());
        dto.setUsername(student.getUser().getUsername());
        dto.setCity(student.getCity().getCity());
        return dto;
    }

    private Student mapToRep(StudentInsertDTO dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setGender(dto.getGender());
        try {
            student.setBirthDate(DateUtil.toDate(dto.getBirthDate()));
        }catch(ParseException e){
            e.printStackTrace();
        }
        student.addCity(repCities.findCityById(dto.getCity()));
        student.addUser(repUsers.findUserById(dto.getUserId()));
        return student;
    }
    private Student mapToRep(StudentUpdateDTO dto) {

        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setGender(dto.getGender());
        try {
            student.setBirthDate(DateUtil.toDate(dto.getBirthDate()));
        }catch(ParseException e){
            e.printStackTrace();
        }
        student.addCity(repCities.findCityById(dto.getCity()));
        student.addUser(repUsers.findUserById(dto.getUserId()));
        return student;
    }
}
