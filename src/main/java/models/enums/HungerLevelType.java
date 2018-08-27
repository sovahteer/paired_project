package models.enums;

public enum HungerLevelType {
    STARVING("Starving"),
    HUNGRY("Hungry"),
    FED("Fed"),
    FULL("Full");

    private final String humanReadable;

    HungerLevelType(String humanReadable) {
        this.humanReadable = humanReadable;
    }

    public String getHumanReadable() {
        return humanReadable;
    }


}
