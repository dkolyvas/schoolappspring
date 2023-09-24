package gr.aueb.cf.schoolpro.repositories;

import gr.aueb.cf.schoolpro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByUsername(String username);
    public User findUserById(Long id);
    @Query("select u from User u where u.student = null  and u.teacher= null")
    public List<User> getUnassignedUsers();
}
