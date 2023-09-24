package gr.aueb.cf.schoolpro.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserInsertDTO {
@Size(min = 2,message = "Username must contain at lest two characters")
    private String username;
@Pattern(regexp = "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[@#$%&!])", message = "Password must contain at least one small, " +
        "one capital letter, a digit and a symbol")
@Size(min =8, message = "Password must be at least 8 characters long")
    private String password;
    private String confirmPassword;
    public UserInsertDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
