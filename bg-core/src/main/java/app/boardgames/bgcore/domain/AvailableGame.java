package app.boardgames.bgcore.domain;

import java.util.HashSet;
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

    public void pushOrRemoveVote(User votingUser) {
        if(votesFromUsers == null || votesFromUsers.isEmpty()) {
            votesFromUsers = new HashSet<>();
            votesFromUsers.add(votingUser);
        } else if (!votesFromUsers.contains(votingUser)) {
            votesFromUsers.add(votingUser);
        } else {
            votesFromUsers.remove(votingUser);
        }
    }
}
