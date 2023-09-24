package gr.aueb.cf.schoolpro.controllers;

import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.dto.UserShowDTO;
import gr.aueb.cf.schoolpro.dto.UserUpdateDTO;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.service.IUsersService;
import gr.aueb.cf.schoolpro.validators.UserInsertValidator;
import gr.aueb.cf.schoolpro.validators.UserUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private IUsersService service;
    private UserUpdateValidator updateValidator;
    private UserInsertValidator insertValidator;
    @Autowired
    public UserRestController(IUsersService service, UserUpdateValidator updateValidator, UserInsertValidator insertValidator) {
        this.service = service;
        this.updateValidator = updateValidator;
        this.insertValidator = insertValidator;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserShowDTO>> getAllUsers(){
        List<UserShowDTO> results;
        try {
            results = service.getUserList();
            return new ResponseEntity<List<UserShowDTO>>(results, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserShowDTO> getUser(@PathVariable("id") Long id){
        UserShowDTO result;
        try{
            result = service.getUserById(id);
            return new ResponseEntity<UserShowDTO>(result, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<UserShowDTO> insertUser(@Valid @RequestBody UserInsertDTO dto, BindingResult errors){
        insertValidator.validate(dto, errors);
        if(errors.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        UserShowDTO result;
        try{
            result = service.InsertUser(dto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(uri).body(result);
        }catch(InsertUpdateError e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserShowDTO> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateDTO dto, BindingResult errors){
        if(id != dto.getId()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        updateValidator.validate(dto, errors);
        if(errors.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        UserShowDTO result;
        try{
            result = service.UpdateUser(dto);
            return new ResponseEntity<UserShowDTO>(result, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserShowDTO> deleteUser(@PathVariable("id") Long id){
        UserShowDTO result;
        try{
            result = service.DeleteUser(id);
            return new ResponseEntity<UserShowDTO>(result, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
