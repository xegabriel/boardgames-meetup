package app.boardgames.bgcore.util;

import app.boardgames.bgcore.domain.badges.BadgeNames;
import app.boardgames.bgcore.domain.badges.Badge;
import app.boardgames.bgcore.domain.badges.HelperBadge;
import app.boardgames.bgcore.domain.badges.KnowsTheRulesBadge;
import app.boardgames.bgcore.domain.badges.VotedMVPBadge;
import app.boardgames.bgcore.exceptions.InvalidBadgeException;

public class BadgeFactory {
    public Badge getBadge(String badgeName) {
        if (badgeName.equalsIgnoreCase(BadgeNames.HELPER.toString())) {
            return new HelperBadge();
        } else if (badgeName.equalsIgnoreCase(BadgeNames.KNOWS_THE_RULES.toString())) {
            return new KnowsTheRulesBadge();
        } else if (badgeName.equalsIgnoreCase(BadgeNames.VOTED_MVP.toString())) {
            return new VotedMVPBadge();
        } else {
            throw new InvalidBadgeException("Badge " + badgeName + " is invalid!");
        }
    }
}
