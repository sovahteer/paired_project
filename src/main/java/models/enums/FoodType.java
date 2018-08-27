package models.enums;

public enum FoodType {

    LEAVES("Leaves",10),
    STEAK("Steak",10),
    GOAT("Goat", 20),
    SHARK("Shark", 50),
    CHICKEN("Chicken", 10),
    VISITOR("Visitor", 40);

    private final String name;
    private final int nutrition;

    FoodType(String name, int nutrition) {
        this.name = name;
        this.nutrition = nutrition;
    }

    public String getName() {
        return name;
    }

    public int getNutrition() {
        return nutrition;
    }
}
