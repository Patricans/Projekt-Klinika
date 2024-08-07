package pl.coderslab.projektklinika.services;

import pl.coderslab.projektklinika.models.User;

public interface UserService {
    void save(User user);
    User findByEmail(String email);
    void changePassword (String email,String oldPassword, String newPassword);
}
