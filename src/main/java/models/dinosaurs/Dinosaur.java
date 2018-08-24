package models.dinosaurs;

import models.enums.DietaryType;
import models.enums.DinosaurType;
import models.paddocks.Paddock;

import javax.persistence.*;

@Entity
@Table(name = "dinosaurs")
@Inheritance(strategy = InheritanceType.JOINED)
public class Dinosaur {

    private boolean hungry;
    private int stomach;
    private DinosaurType species;
    private int id;
    private Paddock paddock;

    public Dinosaur(DinosaurType species) {
        this.species = species;
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

    @Enumerated(value = EnumType.STRING)
    @Column(name = "species")
    public DinosaurType getSpecies() {
        return species;
    }

    public void setSpecies(DinosaurType species) {
        this.species = species;
    }

    @ManyToOne
    @JoinColumn(name = "paddock_id", nullable = true)
    public Paddock getPaddock() {
        return paddock;
    }

    public void setPaddock(Paddock paddock) {
        this.paddock = paddock;
    }


    public boolean checkIfCompatible(Paddock paddock) {
        boolean compatible = false;
        if (paddock.getDietaryType() == this.getSpecies().getDietaryType()) {
            if (this.getSpecies().getDietaryType() == DietaryType.HERBIVORE) {
                if (paddock.getDietaryType() == DietaryType.HERBIVORE) {
                    compatible = true;
                } else {
                    compatible = false;
                }

            } else {
                if (paddock.getDinosaurType() != null) {
                    if(paddock.checkIfOfPaddockType(this) == true) {
                        compatible = true;
                    }
                } else {
                    compatible = true;
                }
            }
        } else {
            compatible = false;
        }
        return compatible;
    }

    public void addPaddockToDinosaur(Paddock paddock) {
        if (checkIfCompatible(paddock)){
            setPaddock(paddock);
        }
    }

    public boolean checkIfPaddockAssigned() {
        if (this.paddock != null) {
            return true;
        } else {
            return false;
        }
    }
}
