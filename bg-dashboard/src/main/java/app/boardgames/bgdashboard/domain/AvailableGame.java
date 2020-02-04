package app.boardgames.bgdashboard.domain;

import java.util.Set;

public class AvailableGame {
    private String gameName;
    private Set<String> votesFromEmail;

    public String getGameName() {
        return gameName;
    }

    public Set<String> getVotesFromEmail() {
        return votesFromEmail;
    }
}
