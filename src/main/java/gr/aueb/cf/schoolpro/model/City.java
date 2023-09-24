package gr.aueb.cf.schoolpro.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CITIES")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "CITY", length = 50, nullable = false)
    private String city;

   @OneToMany(mappedBy = "City")
    private List<Student> students = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student){
        this.students.add(student);
        if(student.getCity() != this) student.setCity(this);
    }
}
