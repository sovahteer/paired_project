package models.enums;


public enum DietaryType {
    CARNIVORE("Carnivore"),
    HERBIVORE("Herbivore"),
    OMNIVORE("Omnivore");

    private final String diet;

    DietaryType(String diet) {
        this.diet = diet;
    }

    public String getDiet() {
        return diet;
    }

}
