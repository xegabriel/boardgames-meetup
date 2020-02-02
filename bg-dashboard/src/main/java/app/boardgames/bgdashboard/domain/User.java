package app.boardgames.bgdashboard.domain;

import app.boardgames.bgdashboard.domain.badges.Badge;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "users")
public class User extends CompactUser {

    private String password;

    private String role;

    private int numberOfAttendedGames = 0;

    private List<Badge> badges;

    private Set<String> decidedEvents;

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

    public Set<String> getDecidedEvents() {
        return decidedEvents;
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

    public void pushDecidedEvent(String event) {
        if(decidedEvents == null) {
            decidedEvents = new HashSet<>();
        }
        decidedEvents.add(event);
    }

}