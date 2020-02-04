package app.boardgames.bgcore.domain;

import java.util.HashSet;
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

    public void pushOrRemoveVote(String votingUser) {
        if (votesFromEmail == null || votesFromEmail.isEmpty()) {
            votesFromEmail = new HashSet<>();
            votesFromEmail.add(votingUser);
        } else if (!votesFromEmail.contains(votingUser)) {
            votesFromEmail.add(votingUser);
        } else {
            votesFromEmail.remove(votingUser);
        }
    }
}
