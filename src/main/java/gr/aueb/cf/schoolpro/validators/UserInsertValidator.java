package gr.aueb.cf.schoolpro.validators;

import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class UserInsertValidator implements Validator {
    private IUsersService service;
@Autowired
    public UserInsertValidator(IUsersService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == UserInsertDTO.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserInsertDTO dto = (UserInsertDTO)target;
        if(dto.getPassword() != dto.getConfirmPassword()){
            errors.rejectValue("password confirmation", "you must type twice the same password");
        }
        if(service.UserExists(dto.getUsername())){
            errors.rejectValue("username", "the username you want to register already exists");
        }

    }
}
