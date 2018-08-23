package models.enums;

public enum DinosaurType {
    TRICERATOPS(30, "Triceratops"),
    TREX(100, "T-Rex"),
    VELOCIRAPTOR(50, "Velociraptor"),
    BRACHIOSAURUS(60, "Brachiosaurus");


    private final int strength;
    private final String name;

    DinosaurType(int strength, String name) {
        this.strength = strength;
        this.name = name;
    }

    public int getStrength(){
        return strength;
    }

    public String getName(){
        return name;
    }


}
