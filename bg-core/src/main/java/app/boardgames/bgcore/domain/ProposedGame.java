package app.boardgames.bgcore.domain;

import java.util.Objects;

public class ProposedGame {
    private String gameName;
    private CompactUser user;

    public ProposedGame(String gameName, CompactUser user) {
        this.gameName = gameName;
        this.user = user;
    }

    public String getGameName() {
        return gameName;
    }

    public CompactUser getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProposedGame that = (ProposedGame) o;
        return Objects.equals(gameName, that.gameName) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameName, user);
    }
}
