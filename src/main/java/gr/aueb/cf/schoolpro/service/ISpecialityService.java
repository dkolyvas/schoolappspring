package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.SpecialityDTO;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;

import java.util.List;

public interface ISpecialityService {
    public List<SpecialityDTO> getSpecialityList() throws NoRecordsFoundException;

    public SpecialityDTO addSpeciality(String speciality) throws InsertUpdateError;

    public SpecialityDTO removeSpeciality(Long id) throws NoRecordsFoundException;
}
