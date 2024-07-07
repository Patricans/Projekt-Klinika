package pl.coderslab.projektklinika.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name="personnel_id", nullable = false)
    @Getter @Setter
    private User user;

    @Column(name="start_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date startDate;

    @Column(name="end_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date endDate;
}

