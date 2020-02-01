package app.boardgames.bgcore.domain;

import java.util.HashSet;
import java.util.Set;

public class AvailableGame {
    private String gameName;
    private Set<CompactUser> votesFromUsers;

    public String getGameName() {
        return gameName;
    }

    public Set<CompactUser> getVotesFromUsers() {
        return votesFromUsers;
    }

    public void pushOrRemoveVote(CompactUser votingUser) {
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
