package app.boardgames.bgauthentication.domain.badges;

public class KnowsTheRulesBadge implements Badge {

    @Override
    public String getBadgeName() {
        return BadgeNames.KNOWS_THE_RULES.toString();
    }
}
