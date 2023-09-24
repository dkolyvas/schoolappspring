package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolpro.dto.MeetingShowDTO;
import gr.aueb.cf.schoolpro.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolpro.model.Meeting;
import gr.aueb.cf.schoolpro.repositories.MeetingsRepository;
import gr.aueb.cf.schoolpro.repositories.StudentRepository;
import gr.aueb.cf.schoolpro.repositories.TeachherRepository;
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
public class MeetingServiceImpl implements IMeetingService{
    private final MeetingsRepository repMeeting;
    private final StudentRepository repStudent;
    private final TeachherRepository repTeacher;
@Autowired
    public MeetingServiceImpl(MeetingsRepository repMeeting, StudentRepository repStudent, TeachherRepository repTeacher) {
        this.repMeeting = repMeeting;
        this.repStudent = repStudent;
        this.repTeacher = repTeacher;
    }


    private MeetingShowDTO mapToDTO(Meeting meeting){
        MeetingShowDTO dto = new MeetingShowDTO();
        dto.setStudentName(meeting.getStudent().getFirstName() + " " + meeting.getStudent().getLastName());
        dto.setTeacherName(meeting.getTeacher().getFirstName() + " " + meeting.getTeacher().getLastName());
        dto.setDate(DateUtil.toString(meeting.getDate()));
        dto.setClassroom(meeting.getRoom());
        dto.setId(meeting.getId());
        return dto;
    }

    private Meeting mapToRep(MeetingInsertDTO dto) throws NoRecordsFoundException {
        Meeting meeting = new Meeting();
        meeting.addStudent(repStudent.findStudentById(dto.getStudentId()));
        meeting.addTeacher(repTeacher.findTeacherById(dto.getTeacherId()));
        if(meeting.getTeacher() == null || meeting.getStudent() ==null) throw new NoRecordsFoundException();
        try {
            meeting.setDate(DateUtil.toDate(dto.getDate()));
        }catch(ParseException e){
            e.printStackTrace();
        }
        meeting.setRoom(dto.getClassroom());
        return meeting;


    }
    private Meeting mapToRep(MeetingUpdateDTO dto) throws NoRecordsFoundException {
        Meeting meeting = new Meeting();
        meeting.setId(dto.getId());
        meeting.addStudent(repStudent.findStudentById(dto.getStudentId()));
        meeting.addTeacher(repTeacher.findTeacherById(dto.getTeacherId()));
        if (meeting.getTeacher() == null || meeting.getStudent() == null) throw new NoRecordsFoundException();
        try {
            meeting.setDate(DateUtil.toDate(dto.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        meeting.setRoom(dto.getClassroom());
        return meeting;
    }
@Transactional
    @Override
    public List<MeetingShowDTO> findTeacherMeetings(Long teacheId) throws NoRecordsFoundException {
        List<MeetingShowDTO> resultList = new ArrayList<>();
        List<Meeting> initialList = repMeeting.findMeetinForTeacher(teacheId);
        if(initialList.isEmpty()) throw new NoRecordsFoundException();
        for(Meeting meeting : initialList){
            resultList.add(mapToDTO(meeting));
        }
        return resultList;
    }
@Transactional
    @Override
    public List<MeetingShowDTO> findStudentMeetings(Long studentId) throws NoRecordsFoundException {
        List<MeetingShowDTO> resultList = new ArrayList<>();
        List<Meeting> initialList = repMeeting.findMeetingsForStudent(studentId);
        if(initialList.isEmpty()) throw new NoRecordsFoundException();
        for(Meeting meeting : initialList){
            resultList.add(mapToDTO(meeting));
        }
        return resultList;
    }
@Transactional
    @Override
    public MeetingShowDTO getMeeting(Long id) throws NoRecordsFoundException {
        Meeting meeting = repMeeting.findMeetingById(id);
        if(meeting == null) throw new NoRecordsFoundException();
        return mapToDTO(meeting);
    }
@Transactional
    @Override
    public MeetingShowDTO insertMeeting(MeetingInsertDTO dto) throws InsertUpdateError, NoRecordsFoundException {
        Meeting meeting = mapToRep(dto);
        repMeeting.save(meeting);
        if(meeting.getId() ==null) throw new InsertUpdateError();
        return mapToDTO(meeting);
    }
@Transactional
    @Override
    public MeetingShowDTO updateMeeting(MeetingUpdateDTO dto) throws NoRecordsFoundException {
        Meeting updatedMeeting;
        Meeting initialMeeting = repMeeting.findMeetingById(dto.getId());
        if(initialMeeting == null) throw new NoRecordsFoundException();
        updatedMeeting = mapToRep(dto);
        repMeeting.save(updatedMeeting);
        return mapToDTO(updatedMeeting);


    }
@Transactional
    @Override
    public MeetingShowDTO deleteMeeting(Long id) throws NoRecordsFoundException {
        Meeting meeting = repMeeting.findMeetingById(id);
        if(meeting == null) throw new NoRecordsFoundException();
        repMeeting.delete(meeting);
        return mapToDTO(meeting);
    }
}
