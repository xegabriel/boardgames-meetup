package app.boardgames.bgcore.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "users")
public class User extends CompactUser {

    private String password;

    private String role;

    private int numberOfAttendedGames = 0;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public int getNumberOfAttendedGames() {
        return numberOfAttendedGames;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void incrementAttendancesNumber() {
        numberOfAttendedGames++;
    }
}
