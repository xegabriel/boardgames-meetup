package app.boardgames.bgdashboard.domain;

import app.boardgames.bgdashboard.configuration.SpringConfiguration;
import app.boardgames.bgdashboard.dao.UserRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class InterestedUser implements Interested {
    private String user;
    private boolean hasConfirmed;
    private Set<BadgeByVoter> badges;

    public String getUser() {
        return user;
    }

    public boolean isHasConfirmed() {
        return hasConfirmed;
    }

    public Set<BadgeByVoter> getBadges() {
        return badges;
    }

    public void confirmAttendance() {
        hasConfirmed = true;
    }

    public void pushBadge(BadgeByVoter badgeByVoter) {
        if (badges == null) {
            badges = new HashSet<>();
        }
        badges.add(badgeByVoter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterestedUser that = (InterestedUser) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public void update(String eventTitle) {
        UserRepository userRepository = (UserRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("userRepository");
        User user = userRepository.findByEmail(getUser());
        user.pushDecidedEvent(eventTitle);
        userRepository.save(user);
    }
}
