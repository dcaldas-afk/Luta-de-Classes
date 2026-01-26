package game.core;

public enum Job {
    WARRIOR("Guerreiro"),
    MAGE("Mago"),
    ARCHER("Atirador");

    private final String displayName;

    Job(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
