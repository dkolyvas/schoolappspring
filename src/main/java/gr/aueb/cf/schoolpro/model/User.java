package gr.aueb.cf.schoolpro.model;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {



@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
@Column(name = "USERNAME")
    private String username;
@Column(name = "PASSWORD")
    private String password;
@OneToOne(mappedBy = "User")
private Teacher teacher;
@OneToOne(mappedBy = "User")
private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addTeacher(Teacher teacher){
        this.teacher = teacher;
        if(teacher.getUser() != this) teacher.setUser(this);
    }

    public Student getStudent() {
        return student;
    }

    public void addStudent(Student student){
        this.student = student;
        if(student.getUser() != this) student.setUser(this);
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
