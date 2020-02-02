package app.boardgames.bgdashboard.domain.badges;

public enum BadgeNames {
    HELPER("Helper"), KNOWS_THE_RULES("KnowsTheRules"), VOTED_MVP("Voted MVP");
    private final String name;

    BadgeNames(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}