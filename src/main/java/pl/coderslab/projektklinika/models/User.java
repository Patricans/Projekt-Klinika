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
    @Getter
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
    @Enumerated(EnumType.STRING)
    private Role role = Role.PATIENT;


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
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @Transient
    @Getter
    @Setter
    private String passwordConfirm;

    public boolean isPatient(){
        return role.equals(Role.PATIENT);
    }
    public boolean isNurse(){
        return role.equals(Role.NURSE);
    }
    public boolean isDoctor(){
        return role.equals(Role.DOCTOR);
    }
    public boolean isAdmin(){
        return role.equals(Role.ADMIN);
    }


    public enum Status {
        ACTIVE, LOCKED, NEW
    }

    public enum Role {
        DOCTOR,
        PATIENT,
        NURSE,
        ADMIN
    }

    @ManyToOne
    @JoinColumn(name="speciality_id",nullable = true)
    @Getter
    @Setter
    private DoctorSpeciality doctorSpecialty;


    public String getDisplayName(){
        return first_name + " " + last_name;
    }
}


