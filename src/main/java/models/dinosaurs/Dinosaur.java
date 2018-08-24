package models.dinosaurs;

import db.DBHelper;
import models.enums.DietaryType;
import models.enums.DinosaurType;
import models.enums.HungerLevelType;
import models.paddocks.Paddock;

import javax.persistence.*;

@Entity
@Table(name = "dinosaurs")
@Inheritance(strategy = InheritanceType.JOINED)
public class Dinosaur {

    private int stomach;
    private DinosaurType species;
    private int id;
    private Paddock paddock;
    private HungerLevelType hungerLevel;

    public Dinosaur(DinosaurType species) {
        this.species = species;
        this.stomach = 100;
        assignHungerLevel();
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

    @Enumerated(value = EnumType.STRING)
    @Column(name = "hunger_level")
    public HungerLevelType getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(HungerLevelType hungerLevel) {
        this.hungerLevel = hungerLevel;
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

    public void assignHungerLevel() {
        if (this.stomach <= 20) {
            setHungerLevel(HungerLevelType.STARVING);
        } else if (this.stomach > 20 && this.stomach <= 50) {
            setHungerLevel(HungerLevelType.HUNGRY);
        } else if (this.stomach > 50 && this.stomach <= 80) {
            setHungerLevel(HungerLevelType.FED);
        } else if (this.stomach > 80) {
            setHungerLevel(HungerLevelType.FULL);
        }
    }
}
