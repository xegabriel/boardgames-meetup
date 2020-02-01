package app.boardgames.bgcore.domain;

import app.boardgames.bgcore.util.Badge;

public class BadgeByVoter {
    private Badge badge;
    private CompactUser voter;

    public BadgeByVoter(Badge badge, CompactUser voter) {
        this.badge = badge;
        this.voter = voter;
    }

    public Badge getBadge() {
        return badge;
    }

    public CompactUser getVoter() {
        return voter;
    }
}
