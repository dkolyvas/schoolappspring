package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.CityDTO;
import gr.aueb.cf.schoolpro.model.City;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;

import java.util.List;

public interface ICityService {
    public List<CityDTO> getCityList() throws NoRecordsFoundException;

    public CityDTO addCity(String city) throws InsertUpdateError;
    public CityDTO deleteCity(Long id) throws NoRecordsFoundException;
}
