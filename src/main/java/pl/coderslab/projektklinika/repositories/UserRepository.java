package pl.coderslab.projektklinika.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.projektklinika.models.User;

import java.util.List;


public interface UserRepository extends CrudRepository <User, Long>{
    @Query ("SELECT u FROM User u WHERE u.email=:email")
    User findByEmail(@Param("email")String email);

    @Query("SELECT p FROM User p JOIN Visit v ON p = v.patient WHERE v.doctor = :doctor GROUP BY p ORDER BY p.last_name ASC , p.last_name ASC")
    List<User> findDoctorPatients(User doctor);

    @Query("SELECT u FROM User u WHERE u.role = 'DOCTOR' AND u.status = 'ACTIVE' ")
    List<User> findAllActiveDoctors();

    @Query("SELECT u FROM User u WHERE u.role = 'NURSE' AND u.status = 'ACTIVE'")
    List<User> findAllActiveNurses();
}
