package app.boardgames.bgcore.domain.badges;

public class HelperBadge implements Badge {

    @Override
    public String getBadgeName() {
        return BadgeNames.HELPER.toString();
    }
}
