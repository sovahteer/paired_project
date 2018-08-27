package models.enums;

public enum AgeType {
    HATCHLING(20, "Hatchling"),
    ADOLESCENT(50, "Adolescent"),
    ADULT(80, "Adult");

    private final int ageThreshold;
    private final String humanREadable;

    AgeType(int ageThreshold, String humanReadable) {
        this.ageThreshold = ageThreshold;
        this.humanREadable = humanReadable;
    }

    public String getHumanREadable() {
        return humanREadable;
    }

    public int getAgeThreshold() {
        return ageThreshold;
    }
}
