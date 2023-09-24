package gr.aueb.cf.schoolpro.dto;

import javax.validation.constraints.Pattern;

public class MeetingShowDTO  {
    private Long id;

    private String teacherName;

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

    private String studentName;
    protected String classroom;
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be of the form dd-mm-yyyy")
    protected String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

     public MeetingShowDTO(){

    }
}
