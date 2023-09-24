package gr.aueb.cf.schoolpro.repositories;

import gr.aueb.cf.schoolpro.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<City, Long> {
    @Query("select c from City c")
    List<City> getCityList();



    City findCityById(Long id);
}
