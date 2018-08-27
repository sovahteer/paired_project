package models.enums;


public enum DietaryType {
    CARNIVORE("Carnivore"),
    HERBIVORE("Herbivore"),
    OMNIVORE("Omnivore");

    private final String humanReadable;

    DietaryType(String humanReadable) {
        this.humanReadable = humanReadable;
    }

    public String getHumanReadable() {
        return humanReadable;
    }

}
