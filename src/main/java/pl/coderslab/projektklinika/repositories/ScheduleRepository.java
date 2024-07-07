package pl.coderslab.projektklinika.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projektklinika.models.Schedule;
import pl.coderslab.projektklinika.models.User;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    @Query ("SELECT s FROM Schedule s WHERE s.user = :user AND s.endDate > NOW() ORDER BY s.startDate")
    List<Schedule> findByUser(User user);
}
