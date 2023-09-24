package gr.aueb.cf.schoolpro.controllers;

import gr.aueb.cf.schoolpro.dto.CityDTO;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityRestController {
    private ICityService service;
@Autowired
    public CityRestController(ICityService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<CityDTO>> getCities(){
        List<CityDTO> results;
        try{
            results = service.getCityList();
            return new ResponseEntity<List<CityDTO>>(results, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<CityDTO> addCity(@RequestBody String cityName){
        CityDTO result;
        try{
            result = service.addCity(cityName);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(uri).body(result);
        }catch(InsertUpdateError e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CityDTO> deleteCity(@PathVariable("id") Long id){
        try{
            CityDTO result = service.deleteCity(id);
            return new ResponseEntity<CityDTO>(result, HttpStatus.OK);
        }catch(NoRecordsFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
