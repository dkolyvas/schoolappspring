package gr.aueb.cf.schoolpro.validators;

import gr.aueb.cf.schoolpro.dto.UserUpdateDTO;
import gr.aueb.cf.schoolpro.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



@Component
public class UserUpdateValidator implements Validator {



    @Override
    public boolean supports(Class<?> clazz) {
        return UserUpdateDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserUpdateDTO dto = (UserUpdateDTO) target;
        if(dto.getPassword() != dto.getConfirmPassword()){
            errors.rejectValue("confirm password", "you must type twice the same password");
        }

    }
}
