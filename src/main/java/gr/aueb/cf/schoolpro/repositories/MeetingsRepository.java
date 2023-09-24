package gr.aueb.cf.schoolpro.repositories;

import gr.aueb.cf.schoolpro.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingsRepository extends JpaRepository<Meeting, Long> {
    @Query("select m from Meeting  m where m.student.id=?1")
    public List<Meeting> findMeetingsForStudent(Long id);

    public Meeting findMeetingById(Long id);

    @Query("select m from Meeting  m where m.teacher.id = ?1")
    public List<Meeting> findMeetinForTeacher(Long id);
    @Query("select m from Meeting m where m.student.id = ?1 and m.student.id = ?2")
    public Meeting findMeetingForTeacherandStudent(Long studentid, Long teacherid);
}
