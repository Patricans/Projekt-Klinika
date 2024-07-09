package pl.coderslab.projektklinika.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.models.Visit;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Integer> {
    @Query("SELECT v FROM Visit v WHERE v.doctor = :doctor")
    List<Visit> getDoctorVisits(User doctor);
}
