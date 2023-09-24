package gr.aueb.cf.schoolpro.repositories;

import gr.aueb.cf.schoolpro.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findStudentsByLastNameStartingWith(String namelike);
    public Student findStudentById(Long id);
    @Query("select s from Student  s where s.user.username = ?1")
    public Student getStudentByUsername(String username);
}
