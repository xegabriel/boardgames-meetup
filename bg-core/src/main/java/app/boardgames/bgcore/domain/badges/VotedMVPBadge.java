package app.boardgames.bgcore.domain.badges;

public class VotedMVPBadge implements Badge {

    @Override
    public String getBadgeName() {
        return BadgeNames.VOTED_MVP.toString();
    }
}
