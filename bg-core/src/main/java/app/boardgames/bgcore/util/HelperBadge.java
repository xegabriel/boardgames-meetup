package app.boardgames.bgcore.util;

import app.boardgames.bgcore.domain.BadgeNames;

public class HelperBadge implements Badge {

    @Override
    public String getBadgeName() {
        return BadgeNames.HELPER.toString();
    }
}
