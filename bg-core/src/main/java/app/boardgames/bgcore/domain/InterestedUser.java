package app.boardgames.bgcore.domain;

import java.util.Objects;

public class InterestedUser {
    private User user;
    private boolean hasConfirmed;

    public InterestedUser(User user) {
        this.user = user;
        this.hasConfirmed = false;
    }

    public User getUser() {
        return user;
    }

    public boolean isHasConfirmed() {
        return hasConfirmed;
    }

    public void confirmAttendance() {
        hasConfirmed = true;
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
}
