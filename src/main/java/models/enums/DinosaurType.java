package models.enums;

public enum DinosaurType {
    TRICERATOPS(30, "Triceratops", DietryType.HERBIVORE),
    TREX(100, "T-Rex", DietryType.CARNIVORE),
    VELOCIRAPTOR(50, "Velociraptor", DietryType.OMNIVORE),
    BRACHIOSAURUS(60, "Brachiosaurus", DietryType.HERBIVORE);


    private final int strength;
    private final String name;
    private final DietryType dietryType;

    DinosaurType(int strength, String name, DietryType dietryType) {
        this.strength = strength;
        this.name = name;
        this.dietryType = dietryType;
    }

    public int getStrength(){
        return strength;
    }

    public String getName(){
        return name;
    }

    public DietryType getDietryType() {
        return dietryType;
    }
}
