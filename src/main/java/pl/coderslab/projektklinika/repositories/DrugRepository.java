package pl.coderslab.projektklinika.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projektklinika.models.Drug;

import java.util.List;

public interface DrugRepository extends CrudRepository<Drug, Integer> {
    @Query("SELECT d FROM Drug d ORDER BY d.name ASC")
    List<Drug> getAllDrugs();
}
