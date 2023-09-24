package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolpro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolpro.model.Teacher;
import gr.aueb.cf.schoolpro.repositories.SpecialitiesRepository;
import gr.aueb.cf.schoolpro.repositories.TeachherRepository;
import gr.aueb.cf.schoolpro.repositories.UserRepository;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeachersServiceImpl implements ITeachersService{

    private final TeachherRepository teacherRep;
    private final UserRepository userRep;
    private final SpecialitiesRepository specialityRep;

@Autowired
    public TeachersServiceImpl(TeachherRepository teacherRep, UserRepository userRep, SpecialitiesRepository specialityRep) {
        this.teacherRep = teacherRep;
        this.userRep = userRep;
        this.specialityRep = specialityRep;
    }
@Transactional
    @Override
    public List<TeacherDTO> getTeacherByLastName(String name) throws NoRecordsFoundException{
        List<Teacher> initialList = teacherRep.findTeachersByLastNameStartingWith(name);
        if (initialList.isEmpty()) throw new NoRecordsFoundException();
        List<TeacherDTO> resultList = new ArrayList<>();
        for(Teacher teacher : initialList){
            resultList.add(mapToDTO(teacher));
        }
        return resultList;
    }
@Transactional
    @Override
    public TeacherDTO getTeacherById(Long id)  throws NoRecordsFoundException{
        Teacher teacher = teacherRep.findTeacherById(id);
        if(teacher == null) throw new NoRecordsFoundException();
        return mapToDTO(teacher);
    }
@Transactional
    @Override
    public TeacherDTO InsertTeacher(TeacherInsertDTO dto) throws InsertUpdateError {
        Teacher teacher = mapToRep(dto);
        teacherRep.save(teacher);
        if(teacher.getId()==0) throw new InsertUpdateError();
        return mapToDTO(teacher);


    }
@Transactional
    @Override
    public TeacherDTO UpdateTeacher(TeacherUpdateDTO dto) throws NoRecordsFoundException{
        Teacher initialTeacher = teacherRep.findTeacherById(dto.getId());
        if(initialTeacher== null ) throw new NoRecordsFoundException();
        Teacher updatedTeacher = mapToRep(dto);
        teacherRep.save(updatedTeacher);
        return mapToDTO(updatedTeacher);

    }
@Transactional
    @Override
    public TeacherDTO DeleteTeacher(Long id) throws NoRecordsFoundException{
        Teacher teacher = teacherRep.findTeacherById(id);
        if(teacher == null) throw new NoRecordsFoundException();
        teacherRep.delete(teacher);
        return mapToDTO(teacher);

    }

    private TeacherDTO mapToDTO(Teacher teacher)
    {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(teacher.getId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setSsn(Integer.toString(teacher.getSSN()));
        dto.setUserName(teacher.getUser().getUsername());
        dto.setSpeciality(teacher.getSpeciality().getSpeciality());
        return dto;
    }

    private Teacher mapToRep(TeacherInsertDTO dto){
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setSSN( Integer.parseInt( dto.getSsn()));
        teacher.addUser(userRep.findUserById(dto.getUserId()));
        teacher.addSpeciality(specialityRep.findSpecialityById(dto.getSpeciality()));
        return teacher;
    }

    private Teacher mapToRep(TeacherUpdateDTO dto){
        Teacher teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setSSN( Integer.parseInt( dto.getSsn()));
        teacher.addUser(userRep.findUserById(dto.getUserId()));
        teacher.addSpeciality(specialityRep.findSpecialityById(dto.getSpeciality()));
        return teacher;
    }


}
