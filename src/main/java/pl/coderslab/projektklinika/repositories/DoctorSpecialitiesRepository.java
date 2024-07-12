package pl.coderslab.projektklinika.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projektklinika.models.DoctorSpeciality;

import java.util.List;

public interface DoctorSpecialitiesRepository extends CrudRepository<DoctorSpeciality, Integer> {
    @Query("SELECT ds FROM DoctorSpeciality ds JOIN User u ON u.doctorSpecialty = ds GROUP BY ds ORDER BY ds.description")
    List<DoctorSpeciality> getAllSorted();
}
