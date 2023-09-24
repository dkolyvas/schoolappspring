package gr.aueb.cf.schoolpro.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StudentShowDTO {

    private Long id;
    @Size(min = 3, max = 50, message = "First name must be 3-50 letters long")
    @Pattern(regexp = "^[a-zA-Z\\-].$", message = "First name must contain only letters")
    private String firstName;
    @Size(min = 3, max = 50, message = "First name must be 3-50 letters long")
    @Pattern(regexp = "^[a-zA-Z\\-].$", message = "First name must contain only letters")
    private String lastName;
    @Pattern(regexp = "^[MF]$", message = "Gender must be eithe F or M")
    private String gender;
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be of the form dd-mm-yyyy")
    private String birthDate;
    private String city;
    private String username;
    public StudentShowDTO(){}

    public StudentShowDTO(Long id, String firstName, String lastName, String gender, String birthDate, String city, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.city = city;
        this.username = username;
    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
