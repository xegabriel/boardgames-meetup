package app.boardgames.bgcore.domain;

import app.boardgames.bgcore.util.Badge;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User extends CompactUser {

    private String password;

    private String role;

    private int numberOfAttendedGames = 0;

    private List<Badge> badges;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getNumberOfAttendedGames() {
        return numberOfAttendedGames;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void pushBadge(Badge badge) {
        if (badges == null) {
            badges = new ArrayList<>();
        }
        badges.add(badge);
    }

    public void incrementAttendancesNumber() {
        numberOfAttendedGames++;
    }

}
