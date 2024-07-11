package pl.coderslab.projektklinika.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projektklinika.models.ReceiptDrug;

public interface ReceiptDrugRepository extends CrudRepository<ReceiptDrug, Integer> {
}
