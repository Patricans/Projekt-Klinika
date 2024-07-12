package pl.coderslab.projektklinika.forms;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.projektklinika.components.AvailableTimeslot;
import pl.coderslab.projektklinika.models.User;

@Setter
@Getter

public class SelectTimeslot {
    private User doctor;
    private AvailableTimeslot selectedTimeslot;
}
