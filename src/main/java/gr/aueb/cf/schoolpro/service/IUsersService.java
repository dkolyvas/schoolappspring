package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.dto.UserShowDTO;
import gr.aueb.cf.schoolpro.dto.UserUpdateDTO;
import gr.aueb.cf.schoolpro.model.User;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;

import java.util.List;

public interface IUsersService {

    public boolean UserExists(String username);
    public UserShowDTO getUserById(Long id) throws NoRecordsFoundException;
    public List<UserShowDTO> getUserList() throws NoRecordsFoundException;
    public UserShowDTO InsertUser(UserInsertDTO dto) throws InsertUpdateError;
    public UserShowDTO UpdateUser(UserUpdateDTO dto) throws NoRecordsFoundException;
    public UserShowDTO DeleteUser(Long id) throws NoRecordsFoundException;

}
