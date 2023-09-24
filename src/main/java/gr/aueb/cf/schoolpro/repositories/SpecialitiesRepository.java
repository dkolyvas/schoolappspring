package gr.aueb.cf.schoolpro.repositories;

import gr.aueb.cf.schoolpro.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialitiesRepository extends JpaRepository<Speciality, Long> {
    @Query("select s from Speciality s")
    List<Speciality> getSpecialityList();

    Speciality findSpecialityById(Long id);

}
