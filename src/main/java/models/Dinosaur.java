package models;

public abstract class Dinosaur {

    private boolean hungry;
    private int stomach;
    private String species;
    private int strength;

    public Dinosaur(String species, int strength) {
        this.species = species;
        this.strength = strength;
        this.stomach = 100;
        this.hungry = false;
    }

    public Dinosaur(){
    }

    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public int getStomach() {
        return stomach;
    }

    public void setStomach(int stomach) {
        this.stomach = stomach;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
