package app.boardgames.bgauthentication.util;

import app.boardgames.bgauthentication.domain.CustomClaims;
import app.boardgames.bgauthentication.domain.User;
import app.boardgames.bgauthentication.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getRoleFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get(CustomClaims.ROLE.toString());
    }

    public String getEmailFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get(CustomClaims.EMAIL.toString());
    }

    public String getFirstNameFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get(CustomClaims.FIRST_NAME.toString());
    }

    public String getLastNameFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get(CustomClaims.LAST_NAME.toString());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        User user = userService.getUserByEmail(userDetails.getUsername());
        claims.put(CustomClaims.EMAIL.toString(), user.getEmail());
        claims.put(CustomClaims.FIRST_NAME.toString(), user.getFirstName());
        claims.put(CustomClaims.LAST_NAME.toString(), user.getLastName());
        claims.put(CustomClaims.ROLE.toString(), user.getRole());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}