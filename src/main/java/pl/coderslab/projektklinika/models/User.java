package pl.coderslab.projektklinika.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @Transient
    private String password;

    @Getter
    @Setter
    @Column(name = "password")
    private String encryptedPassword;

    @Getter
    @Setter
    private Role role;

    @Getter
    @Setter
    private String first_name;

    @Getter
    @Setter
    private String last_name;

    @Getter
    @Setter
    private String pesel;

    @Setter
    @Getter
    private Status status;

    @Transient
    @Getter
    @Setter
    private String passwordConfirm;

    public enum Status {
        ACTIVE, LOCKED, NEW
    };

    public enum Role {
        DOCTOR,
        PATIENT,
        NURSE,
        ADMIN
    };
}
