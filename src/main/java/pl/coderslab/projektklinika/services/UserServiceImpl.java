package pl.coderslab.projektklinika.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException(email);
        }
        if(!bCryptPasswordEncoder.matches(oldPassword, user.getEncryptedPassword())){
            throw new BadCredentialsException("Błędne hasło");
        }
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
