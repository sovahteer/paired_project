package models.enums;

public enum DietryType {
    CARNIVORE("Carnivore"),
    HERBIVORE("Herbivore"),
    OMNIVORE("Omnivore");

    private final String diet;

    DietryType(String diet) {
        this.diet = diet;
    }

    public String getDiet() {
        return diet;
    }
}
