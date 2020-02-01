package app.boardgames.bgdashboard.domain;

import java.util.Set;

public class AvailableGame {
    private String gameName;
    private Set<User> votesFromUsers;

    public String getGameName() {
        return gameName;
    }

    public Set<User> getVotesFromUsers() {
        return votesFromUsers;
    }
}
