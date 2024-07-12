package pl.coderslab.projektklinika.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user_score")
@Getter
public class UserScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    private User user;

    @Column(name="score")
    @Setter
    private int score;

    @ManyToOne
    @JoinColumn(name = "issuer_id", nullable = false)
    @Setter
    private User issuer;
}
