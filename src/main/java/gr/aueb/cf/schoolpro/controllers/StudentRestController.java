package gr.aueb.cf.schoolpro.controllers;

import gr.aueb.cf.schoolpro.dto.StudentShowDTO;
import gr.aueb.cf.schoolpro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolpro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.service.IStudentsService;
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
@RequestMapping("/students")
public class StudentRestController {


    private final IStudentsService service;

    @Autowired
    public StudentRestController(IStudentsService service) {
        this.service = service;
    }
    @GetMapping("/")
    public ResponseEntity<List<StudentShowDTO>> getStudentsByLastName(@RequestParam("lastname") String lastname){
        List<StudentShowDTO> resultList;
        try{
            resultList = service.getStudentsByLastName(lastname);
            return new ResponseEntity<List<StudentShowDTO>>(resultList, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentShowDTO> getStudentById(@PathVariable("id") Long id){
        StudentShowDTO student;
        try{
            student = service.getStudentById(id);
            return new ResponseEntity<StudentShowDTO>(student, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<StudentShowDTO> insertStudent(@Valid @RequestBody StudentInsertDTO dto, BindingResult errors){

        StudentShowDTO newStudent;
        if(errors.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try{
            newStudent = service.insertStudent(dto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(newStudent.getId()).toUri();
            return ResponseEntity.created(uri).body(newStudent);
        }catch(InsertUpdateError e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentShowDTO> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody StudentUpdateDTO dto, BindingResult errors){
        StudentShowDTO updatedStudent;
        if(id != dto.getId()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if(errors.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try{
            updatedStudent = service.updateStudent(dto);
            return new ResponseEntity<StudentShowDTO>(updatedStudent, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StudentShowDTO> deleteStudent(@PathVariable("id") Long id){
        StudentShowDTO student;
        try{
            student = service.deleteStudent(id);
            return new ResponseEntity<StudentShowDTO>(student, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }




}
