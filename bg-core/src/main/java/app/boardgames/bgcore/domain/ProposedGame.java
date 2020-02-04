package app.boardgames.bgcore.domain;

import java.util.Objects;

public class ProposedGame {
    private String gameName;
    private String userEmail;

    public ProposedGame(String gameName, String userEmail) {
        this.gameName = gameName;
        this.userEmail = userEmail;
    }

    public String getGameName() {
        return gameName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProposedGame that = (ProposedGame) o;
        return Objects.equals(gameName, that.gameName) &&
                Objects.equals(userEmail, that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameName, userEmail);
    }
}
