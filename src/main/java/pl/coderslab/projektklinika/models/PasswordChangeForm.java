package pl.coderslab.projektklinika.models;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PasswordChangeForm {
    private String oldPassword;
    private String newPassword;
    private String newPassword2;

}
