package gr.aueb.cf.schoolpro.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StudentUpdateDTO { @Size(min = 3, max = 50, message = "First name must be 3-50 letters long")
@Pattern(regexp = "^[a-zA-Z\\-].$", message = "First name must contain only letters")
private String firstName;
    @Size(min = 3, max = 50, message = "First name must be 3-50 letters long")
    @Pattern(regexp = "^[a-zA-Z\\-].$", message = "First name must contain only letters")
    private String lastName;
    @Pattern(regexp = "^[MF]$", message = "Gender must be eithe F or M")
    private String gender;
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be of the form dd-mm-yyyy")
    private String birthDate;
    private Long city;
    private Long userId;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentUpdateDTO(){}



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

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
