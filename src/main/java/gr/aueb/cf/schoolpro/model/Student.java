package gr.aueb.cf.schoolpro.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "FIRSTNAME", length = 50, nullable = false)
    private String firstName;
    @Column(name = "LASTNAME", length = 50, nullable = false)
    private String lastName;
    @Column(name = "GENDER", length = 1, nullable = false)
    private String gender;
    @Column(name = "BIRTH_DATE", nullable = true)
    private Date birthDate;
    @ManyToOne
    @JoinColumn(name = "CITY_ID", nullable = true)
    private City city;
    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    @OneToMany(mappedBy = "Student")
    private List<Meeting> meetings = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void addCity(City city){
        this.city = city;
        for(Student student : city.getStudents()){
            if(student == this) return;
        }
        city.getStudents().add(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUser(User user) {
        this.user = user;
        if(user.getStudent() != this) user.setStudent(this);
    }


    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public void addMeeting(Meeting meeting){
        this.meetings.add(meeting);
        if(meeting.getStudent() != this) meeting.setStudent(this);
    }

    public Student(){}


}
