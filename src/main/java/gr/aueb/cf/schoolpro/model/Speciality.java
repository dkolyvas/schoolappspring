package gr.aueb.cf.schoolpro.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SPECIALITIES")
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "SPECIALITY", length = 50, nullable = false)
    private String speciality;
    @OneToMany(mappedBy = "Speciality")
    private List<Teacher> teachers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher){
        this.teachers.add(teacher);
        if(teacher.getSpeciality() != this) teacher.setSpeciality(this);
    }
}
