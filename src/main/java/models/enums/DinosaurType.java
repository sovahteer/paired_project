package models.enums;

public enum DinosaurType {
    TRICERATOPS(30, "Triceratops", DietaryType.HERBIVORE),
    TREX(100, "T-Rex", DietaryType.CARNIVORE),
    VELOCIRAPTOR(50, "Velociraptor", DietaryType.OMNIVORE),
    BRACHIOSAURUS(60, "Brachiosaurus", DietaryType.HERBIVORE);


    private final int strength;
    private final String name;
    private final DietaryType dietaryType;

    DinosaurType(int strength, String name, DietaryType dietaryType) {
        this.strength = strength;
        this.name = name;
        this.dietaryType = dietaryType;
    }

    public int getStrength(){
        return strength;
    }

    public String getName(){
        return name;
    }

    public DietaryType getDietaryType() {
        return dietaryType;
    }
}
