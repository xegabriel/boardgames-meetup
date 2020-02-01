package app.boardgames.bgauthentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    private final static String GRANTED_AUTHORITY_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        app.boardgames.bgauthentication.domain.User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }

        return new User(user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(GRANTED_AUTHORITY_PREFIX + user.getRole())));
    }
}