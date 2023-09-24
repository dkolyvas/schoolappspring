package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.dto.UserShowDTO;
import gr.aueb.cf.schoolpro.dto.UserUpdateDTO;
import gr.aueb.cf.schoolpro.model.User;
import gr.aueb.cf.schoolpro.repositories.UserRepository;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements IUsersService {
    private final UserRepository repository;
@Autowired
    public UsersServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean UserExists(String username) {
        User user = repository.findUserByUsername(username);
        return (user != null);
    }

    @Override
    public UserShowDTO getUserById(Long id) throws NoRecordsFoundException {
        User user = repository.findUserById(id);
        UserShowDTO dto = new UserShowDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        if(user == null) throw new NoRecordsFoundException();
        return dto;
    }

    @Override
    public List<UserShowDTO> getUserList() throws NoRecordsFoundException {
        List<UserShowDTO> resultList = new ArrayList<>();
        List<User> initialList = repository.getUnassignedUsers();
        if(initialList.isEmpty()) throw new NoRecordsFoundException();
        for(User user : initialList) {
            resultList.add(new UserShowDTO(user.getId(), user.getUsername()));
        }
        return resultList;
    }
@Transactional
    @Override
    public UserShowDTO InsertUser(UserInsertDTO dto) throws InsertUpdateError {
        User user = new User();
        UserShowDTO result = new UserShowDTO();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        repository.save(user);
        if(user.getId() == null) throw new InsertUpdateError();
        result.setUsername(user.getUsername());
        result.setId(user.getId());
        return result;

    }
@Transactional
    @Override
    public UserShowDTO UpdateUser(UserUpdateDTO dto) throws NoRecordsFoundException {
        User user = new User();
        UserShowDTO result = new UserShowDTO();
        User initialUser = repository.findUserById(dto.getId());
        if(initialUser == null) throw new NoRecordsFoundException();
        user.setId(dto.getId());
        user.setUsername(initialUser.getUsername());
        user.setPassword(dto.getPassword());
        repository.save(user);
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        return result;

    }
@Transactional
    @Override
    public UserShowDTO DeleteUser(Long id) throws NoRecordsFoundException {
        User user = repository.findUserById(id);
        UserShowDTO result = new UserShowDTO();
        if(user == null) throw new NoRecordsFoundException();
        result.setUsername(user.getUsername());
        result.setId(user.getId());
        repository.delete(user);
        return result;


    }
}
