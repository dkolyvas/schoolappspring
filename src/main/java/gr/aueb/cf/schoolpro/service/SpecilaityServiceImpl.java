package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.SpecialityDTO;
import gr.aueb.cf.schoolpro.model.Speciality;
import gr.aueb.cf.schoolpro.repositories.SpecialitiesRepository;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecilaityServiceImpl implements ISpecialityService {

    private final SpecialitiesRepository repository;
@Autowired
    public SpecilaityServiceImpl(SpecialitiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SpecialityDTO> getSpecialityList()  throws NoRecordsFoundException{
        List<SpecialityDTO> resultList = new ArrayList<>();
        List<Speciality> initialList = repository.getSpecialityList();
        if(initialList.isEmpty()) throw new NoRecordsFoundException();
        for(Speciality speciality : initialList){
            resultList.add(new SpecialityDTO(speciality.getId(), speciality.getSpeciality()));
        }
        return resultList;
    }
@Transactional
    @Override
    public SpecialityDTO addSpeciality(String speciality) throws InsertUpdateError {
            Speciality newSpeciality = new Speciality();
            newSpeciality.setSpeciality(speciality);
            repository.save(newSpeciality);
            if(newSpeciality.getId() == null) throw new InsertUpdateError();
            return new SpecialityDTO(newSpeciality.getId(), newSpeciality.getSpeciality());

    }
@Transactional
    @Override
    public SpecialityDTO removeSpeciality(Long id) throws NoRecordsFoundException {
            Speciality speciality = repository.findSpecialityById(id);
            if(speciality == null) throw new NoRecordsFoundException();
            SpecialityDTO dto = new SpecialityDTO(speciality.getId(), speciality.getSpeciality());
            repository.delete(speciality);
            return dto;

    }
}
