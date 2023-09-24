package gr.aueb.cf.schoolpro.controllers;

import gr.aueb.cf.schoolpro.dto.StudentShowDTO;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolpro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.service.ITeachersService;
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
@RequestMapping("/teachers")
public class TeacherRestController {
    private ITeachersService service;

    @Autowired
    public TeacherRestController(ITeachersService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<TeacherDTO>> getTeachersByLastName(@RequestParam("lastname")String nameLike){
        List<TeacherDTO> resultList;
        try{
            resultList = service.getTeacherByLastName(nameLike);
            return new ResponseEntity<List<TeacherDTO>>(resultList, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable("id") Long id){
        TeacherDTO result;
        try{
            result = service.getTeacherById(id);
            return new ResponseEntity<TeacherDTO>(result, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<TeacherDTO> insertTeacher(@Valid @RequestBody TeacherInsertDTO dto, BindingResult errors){
        TeacherDTO result;
        if(errors.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try{
            result = service.InsertTeacher(dto);
            Long id = result.getId();
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(uri).body(result);
        }catch(InsertUpdateError e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable("id") Long id, @Valid @RequestBody TeacherUpdateDTO dto,  BindingResult errors){
        if(errors.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(id != dto.getId()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        TeacherDTO result ;
        try{
            result = service.UpdateTeacher(dto);
            return new ResponseEntity<TeacherDTO>(result, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherDTO> deleteTeacher(@PathVariable("id") Long id){
        TeacherDTO result;
        try{
            result = service.DeleteTeacher(id);
            return new ResponseEntity<TeacherDTO>(result, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
