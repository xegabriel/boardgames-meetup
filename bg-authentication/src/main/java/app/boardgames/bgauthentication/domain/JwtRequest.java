package app.boardgames.bgauthentication.domain;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private String username;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest() {

    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}