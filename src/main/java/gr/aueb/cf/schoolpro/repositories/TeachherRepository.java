package gr.aueb.cf.schoolpro.repositories;

import gr.aueb.cf.schoolpro.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachherRepository extends JpaRepository<Teacher, Long> {
    public List<Teacher> findTeachersByLastNameStartingWith(String namelike);
    public Teacher findTeacherById( Long id);
    @Query("select t from Teacher  t where t.user.username = ?1")
    public Teacher findTeachersByUsername(String username);
}
