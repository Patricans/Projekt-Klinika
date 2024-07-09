package pl.coderslab.projektklinika.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitComment {
    private int visitId;
    private String comment;
}
