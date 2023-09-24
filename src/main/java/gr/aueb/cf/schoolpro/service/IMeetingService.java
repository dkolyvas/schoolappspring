package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolpro.dto.MeetingShowDTO;
import gr.aueb.cf.schoolpro.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;

import java.util.List;

public interface IMeetingService {
    public List<MeetingShowDTO> findTeacherMeetings(Long teacheId) throws NoRecordsFoundException;
    public List<MeetingShowDTO> findStudentMeetings(Long studentId) throws NoRecordsFoundException;
    public MeetingShowDTO getMeeting(Long id) throws NoRecordsFoundException;

    public MeetingShowDTO insertMeeting(MeetingInsertDTO dto) throws InsertUpdateError, NoRecordsFoundException;
    public MeetingShowDTO updateMeeting(MeetingUpdateDTO dto) throws NoRecordsFoundException;
    public MeetingShowDTO deleteMeeting(Long id) throws NoRecordsFoundException;
}
