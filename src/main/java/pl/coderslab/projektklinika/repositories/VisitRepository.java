package pl.coderslab.projektklinika.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.models.Visit;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Integer> {
    @Query("SELECT v FROM Visit v WHERE v.doctor = :doctor ORDER BY v.startDate ASC")
    List<Visit> getAllDoctorVisits(User doctor);

    @Query("SELECT v FROM Visit v WHERE v.doctor = :doctor AND v.startDate + 1 hour >= NOW() ORDER BY v.startDate ASC")
    List<Visit> getFutureDoctorVisits(User doctor);

    @Query("SELECT v FROM Visit v WHERE v.id = :id AND v.doctor = :doctor")
    Visit getVisitById(int id, User doctor);

    @Query("SELECT v FROM Visit v WHERE v.patient = :patient ORDER BY v.startDate DESC")
    List<Visit> getPatientVisits(User patient);
}
