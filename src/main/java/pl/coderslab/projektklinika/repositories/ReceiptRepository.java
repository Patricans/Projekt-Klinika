package pl.coderslab.projektklinika.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projektklinika.models.EReceipt;
import pl.coderslab.projektklinika.models.User;

import java.util.List;

public interface ReceiptRepository extends CrudRepository<EReceipt, Integer> {
    @Query("SELECT e FROM EReceipt e WHERE e.patient = :patient ORDER BY e.date DESC")
    List<EReceipt> findByPatient(User patient);

}
