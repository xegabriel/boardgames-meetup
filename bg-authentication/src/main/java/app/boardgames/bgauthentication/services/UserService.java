package app.boardgames.bgauthentication.services;

import app.boardgames.bgauthentication.dao.UserRepository;
import app.boardgames.bgauthentication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final static String USER_ROLE = "user";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public User getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setRole(USER_ROLE);
        return userRepository.save(user);
    }

}
