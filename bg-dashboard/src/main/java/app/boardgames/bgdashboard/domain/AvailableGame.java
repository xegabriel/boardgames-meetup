package app.boardgames.bgdashboard.domain;

import java.util.Set;

public class AvailableGame {
    private String gameName;
    private Set<VotingUser> votesFromUsers;

    public String getGameName() {
        return gameName;
    }

    public Set<VotingUser> getVotesFromUsers() {
        return votesFromUsers;
    }
}
