package pl.coderslab.projektklinika.components;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.coderslab.projektklinika.models.ReceiptDrug;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class DrugCart {
    private final List<ReceiptDrug> drugs = new ArrayList<>();
}
