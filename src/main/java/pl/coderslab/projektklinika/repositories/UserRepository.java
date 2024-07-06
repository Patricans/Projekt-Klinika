package pl.coderslab.projektklinika.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.projektklinika.models.User;


public interface UserRepository extends CrudRepository <User, Long>{
    @Query ("SELECT u FROM User u WHERE u.email = email")
    User findByEmail(@Param("email")String email);
}
