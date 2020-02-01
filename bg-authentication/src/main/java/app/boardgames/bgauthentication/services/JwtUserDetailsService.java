package app.boardgames.bgauthentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final static String GRANTED_AUTHORITY_PREFIX = "ROLE_";
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        app.boardgames.bgauthentication.domain.User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }

        return new User(user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(GRANTED_AUTHORITY_PREFIX + user.getRole())));
    }
}