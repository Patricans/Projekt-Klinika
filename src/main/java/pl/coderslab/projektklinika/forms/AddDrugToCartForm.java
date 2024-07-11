package pl.coderslab.projektklinika.forms;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.projektklinika.models.Drug;

@Getter
@Setter
public class AddDrugToCartForm {
    private Drug drug;
    private int quantity = 1;
}
