package gr.aueb.cf.schoolpro.dto;

import javax.validation.constraints.Pattern;

public class MeetingUpdateDTO {
    protected Long teacherId;
    protected Long studentId;
    protected String classroom;
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be of the form dd-mm-yyyy")
    protected String date;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeetingUpdateDTO(){}

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
