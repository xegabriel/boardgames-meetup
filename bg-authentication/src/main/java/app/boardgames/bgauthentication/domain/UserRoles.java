package app.boardgames.bgauthentication.domain;

public enum UserRoles {
    USER("USER"), ORGANIZER("ORGANIZER");
    private final String text;

    UserRoles(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
