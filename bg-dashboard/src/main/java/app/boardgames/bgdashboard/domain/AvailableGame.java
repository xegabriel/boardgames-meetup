package app.boardgames.bgdashboard.domain;

import java.util.Set;

public class AvailableGame {
    private String gameName;
    private Set<CompactUser> votesFromCompactUsers;

    public String getGameName() {
        return gameName;
    }

    public Set<CompactUser> getVotesFromCompactUsers() {
        return votesFromCompactUsers;
    }
}
