package app.boardgames.bgcore.domain;

import app.boardgames.bgcore.domain.badges.Badge;

public class BadgeByVoter {
    private Badge badge;
    private String voter;

    public BadgeByVoter(Badge badge, String voter) {
        this.badge = badge;
        this.voter = voter;
    }

    public Badge getBadge() {
        return badge;
    }

    public String getVoter() {
        return voter;
    }
}
