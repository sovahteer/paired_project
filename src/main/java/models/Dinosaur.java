package models;

import javax.persistence.*;

@Entity
@Table(name = "dinosaurs")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Dinosaur {

    private boolean hungry;
    private int stomach;
    private String species;
    private int strength;
    private int id;

    public Dinosaur(String species, int strength) {
        this.species = species;
        this.strength = strength;
        this.stomach = 100;
        this.hungry = false;
    }

    public Dinosaur(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "hungry")
    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    @Column(name = "stomach")
    public int getStomach() {
        return stomach;
    }

    public void setStomach(int stomach) {
        this.stomach = stomach;
    }

    @Column(name = "species")
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Column(name = "strength")
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
