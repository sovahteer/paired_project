package models.enums;

public enum FoodType {

    LEAVES(10),
    STEAK(10),
    GOAT(20),
    SHARK(50);

    private final int nutrition;

    FoodType(int nutrition) {
        this.nutrition = nutrition;
    }

    public int getNutrition() {
        return nutrition;
    }
}
