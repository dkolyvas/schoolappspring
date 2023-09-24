package gr.aueb.cf.schoolpro.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEACHERS")
public class Teacher {

    public Long getId() {
        return id;
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

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name ="FIRSTNAME", length = 50, nullable = false)
    private String firstName;
    @Column(name = "LASTNAME", length = 50, nullable = false)
    private String lastName;
    @Column(name = "SSN", length = 9, nullable = false)
    private int SSN;
    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = true)
    private User user;
    @ManyToOne
    @JoinColumn(name = "SPECIALITY_ID", nullable = true)
    private Speciality speciality;
    @OneToMany(mappedBy = "Teacher")
    private List<Meeting> meetings = new ArrayList<>();

    public Teacher() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUser(User user) {

        this.user = user;
        if(user.getTeacher()== this) return;
        user.setTeacher(this);
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public void addSpeciality(Speciality speciality){
        this.speciality = speciality;
        for(Teacher teacher :speciality.getTeachers()){
            if(teacher == this) return;
        }
        speciality.getTeachers().add(this);
    }
    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public void addMeeting(Meeting meeting){
        this.meetings.add(meeting);
        if(meeting.getTeacher() != this)  meeting.setTeacher(this);
    }
}
