package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.CityDTO;
import gr.aueb.cf.schoolpro.model.City;
import gr.aueb.cf.schoolpro.repositories.CitiesRepository;
import gr.aueb.cf.schoolpro.service.Exceptions.InsertUpdateError;
import gr.aueb.cf.schoolpro.service.Exceptions.NoRecordsFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements ICityService {
    private final CitiesRepository repository;
@Autowired
    public CityServiceImpl(CitiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CityDTO> getCityList() throws NoRecordsFoundException{
        List<CityDTO> resultList = new ArrayList<>();
        List<City> initialList = repository.getCityList();
        if(initialList.isEmpty()) throw new NoRecordsFoundException();
        for(City city : initialList){
            resultList.add(new CityDTO(city.getId(), city.getCity()));
        }
        return resultList;
    }
@Transactional
    @Override
    public CityDTO addCity(String city) throws InsertUpdateError {
            City newCity = new City();
            newCity.setCity(city);
            repository.save(newCity);
            if(newCity.getId() == null) throw new InsertUpdateError();
            return new CityDTO(newCity.getId(), newCity.getCity());
    }
    @Transactional
    @Override
    public CityDTO deleteCity(Long id) throws NoRecordsFoundException {
            City city = repository.findCityById(id);
            if(city == null) throw new NoRecordsFoundException();
            CityDTO dto = new CityDTO(city.getId(), city.getCity());
            repository.delete(city);
            return dto;

    }
}
