package gr.aueb.cf.schoolpro.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserUpdateDTO {
    private Long id;

    @Pattern(regexp = "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[@#$%&!])", message = "Password must contain at least one small, " +
            "one capital letter, a digit and a symbol")
    @Size(min =8, message = "Password must be at least 8 characters long")
    private String password;

    private String confirmPassword;
    public UserUpdateDTO(){}

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
