package gr.aueb.cf.schoolpro.controllers;

import gr.aueb.cf.schoolpro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolpro.dto.MeetingShowDTO;
import gr.aueb.cf.schoolpro.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.service.IMeetingService;
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
@RequestMapping("/meetings")
public class MeetingRestController {
    private IMeetingService service;
@Autowired
    public MeetingRestController(IMeetingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingShowDTO> getMeeting(@PathVariable("id") Long id){
        MeetingShowDTO result;
        try{
            result = service.getMeeting(id);
            return new ResponseEntity<MeetingShowDTO>(result, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<List<MeetingShowDTO>> getMeetingsForTeacher(@PathVariable("id") Long id){
        List<MeetingShowDTO> results;
        try{
            results = service.findTeacherMeetings(id);
            return new ResponseEntity<List<MeetingShowDTO>>(results, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<MeetingShowDTO>> getMeetingsForStudent(@PathVariable("id") Long id){
        List<MeetingShowDTO> results;
        try{
            results = service.findStudentMeetings(id);
            return new ResponseEntity<List<MeetingShowDTO>>(results, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<MeetingShowDTO> insertMeeting(@Valid @RequestBody MeetingInsertDTO dto, BindingResult errors){
        if(errors.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        MeetingShowDTO result;
        try{
            result = service.insertMeeting(dto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(uri).body(result);
        }catch(InsertUpdateError| NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<MeetingShowDTO> updateMeeting(@PathVariable("id") Long id, @Valid @RequestBody MeetingUpdateDTO dto, BindingResult errors){
            if(dto.getId() != id) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            if(errors.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            MeetingShowDTO result;
            try{
                result = service.updateMeeting(dto);
                return new ResponseEntity<MeetingShowDTO>(result, HttpStatus.OK);
            }catch(NoRecordsFoundException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MeetingShowDTO> deleteMeeting(@PathVariable("id") Long id){
            MeetingShowDTO result;
            try{
                result = service.deleteMeeting(id);
                return new ResponseEntity<MeetingShowDTO>(result, HttpStatus.OK);
            }catch(NoRecordsFoundException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
}
