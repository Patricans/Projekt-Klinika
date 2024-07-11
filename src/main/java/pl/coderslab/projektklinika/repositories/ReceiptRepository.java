package pl.coderslab.projektklinika.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projektklinika.models.EReceipt;

public interface ReceiptRepository extends CrudRepository<EReceipt, Integer> {
}
