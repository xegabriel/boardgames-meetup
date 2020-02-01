package app.boardgames.bgcore.util;

import app.boardgames.bgcore.domain.BadgeNames;

public class VotedMVPBadge implements Badge {

    @Override
    public String getBadgeName() {
        return BadgeNames.VOTED_MVP.toString();
    }
}
