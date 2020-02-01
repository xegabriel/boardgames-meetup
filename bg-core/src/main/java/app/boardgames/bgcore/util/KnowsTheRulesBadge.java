package app.boardgames.bgcore.util;

import app.boardgames.bgcore.domain.BadgeNames;

public class KnowsTheRulesBadge implements Badge {

    @Override
    public String getBadgeName() {
        return BadgeNames.KNOWS_THE_RULES.toString();
    }
}
