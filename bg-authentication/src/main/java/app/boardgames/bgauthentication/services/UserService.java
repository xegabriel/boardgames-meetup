package app.boardgames.bgauthentication.services;

import app.boardgames.bgauthentication.dao.UserRepository;
import app.boardgames.bgauthentication.domain.User;
import app.boardgames.bgauthentication.domain.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public User getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setRole(UserRoles.USER.toString());
        return userRepository.save(user);
    }

}
