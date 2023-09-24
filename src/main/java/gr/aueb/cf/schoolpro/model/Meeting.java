package gr.aueb.cf.schoolpro.model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "MEETINGS")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", nullable = true)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", nullable = true)
    private Teacher teacher;
    @Column(name = "MEETING_ROOM", nullable = true)
    private String room;
    @Column(name = "MEETING_DATE", nullable = true)
    private Date date;

    public Meeting(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public void addStudent(Student student){
        this.student = student;
        for(Meeting meeting : student.getMeetings()){
            if(meeting == this) return;
        }
        student.getMeetings().add(this);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addTeacher(Teacher teacher){
        this.teacher = teacher;
        for(Meeting meeting : teacher.getMeetings()){
            if(meeting == this) return;
        }
        teacher.getMeetings().add(this);
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
