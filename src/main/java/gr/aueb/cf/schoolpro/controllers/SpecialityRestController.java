package gr.aueb.cf.schoolpro.controllers;

import gr.aueb.cf.schoolpro.dto.SpecialityDTO;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.service.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/specialities")
public class SpecialityRestController {
    ISpecialityService service;
@Autowired
    public SpecialityRestController(ISpecialityService service) {
        this.service = service;
    }
    @GetMapping("/")
    public ResponseEntity<List<SpecialityDTO>> getSpecialityList(){
        try{
            List<SpecialityDTO> results = service.getSpecialityList();
            return new ResponseEntity<List<SpecialityDTO>>(results, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<SpecialityDTO> addSpeciality(@RequestBody String specialityName){
        try{
            SpecialityDTO result = service.addSpeciality(specialityName);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(result);
        }catch(InsertUpdateError e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SpecialityDTO> deleteSpeciality(@PathVariable("id") Long id){
            try{
                SpecialityDTO result = service.removeSpeciality(id);
                return new ResponseEntity<SpecialityDTO>(result, HttpStatus.OK);
            }catch (NoRecordsFoundException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
}
