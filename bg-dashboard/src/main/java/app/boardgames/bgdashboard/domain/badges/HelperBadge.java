package app.boardgames.bgdashboard.domain.badges;

public class HelperBadge implements Badge {

    @Override
    public String getBadgeName() {
        return BadgeNames.HELPER.toString();
    }
}
