package models.dinosaurs;

import db.DBDinosaur;
import db.DBHelper;
import db.DBPark;
import models.enums.*;
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
    private int age;
    private int defaultStrength;

    public Dinosaur(DinosaurType species) {
        this.species = species;
        this.stomach = 100;
        this.age = 0;
        this.defaultStrength = 10;
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

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "strength")
    public int getDefaultStrength() {
        return defaultStrength;
    }

    public void setDefaultStrength(int defaultStrength) {
        this.defaultStrength = defaultStrength;
    }

    public boolean checkIfCompatible(Paddock paddock) {
        boolean compatible = false;
        if (paddock.getDietaryType() == this.getSpecies().getDietaryType()) {
            if (this.getSpecies().getDietaryType() == DietaryType.HERBIVORE) {
                if (paddock.checkIfHerbivore(this)) {
                    compatible = true;
                } else {
                    compatible = false;
                }

            } else {
                if (paddock.getDinosaurType() != null) {
                    if(paddock.checkIfOfPaddockType(this)) {
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
//check if compatible with paddock
    public void addPaddockToDinosaur(Paddock paddock) {
        if (checkIfCompatible(paddock)){
            if (!paddock.checkIfHerbivore(this)) {
                paddock.assignDinosaurType(this.getSpecies());
            }
            setPaddock(paddock);
            paddock.addDinoToPaddock(this);
        }
    }

    public void eat(FoodType foodtype){
        this.stomach += foodtype.getNutrition();
        if (this.stomach > 100){
            this.stomach = 100;
        }
        assignHungerLevel();
    }

    public boolean checkIfPaddockAssigned() {
        if (this.paddock != null) {
            return true;
        } else {
            return false;
        }
    }

    public void assignHungerLevel() {
        setHungerLevel(DBDinosaur.checkHungerLevel(this.stomach));
    }

    public void getStronger(){
        int maxStrength = this.species.getStrength();
        int growthRatio = maxStrength / 10;
        if (this.getDefaultStrength() <= (100 - growthRatio)){
            setDefaultStrength(this.defaultStrength + growthRatio);
        } else {
            setDefaultStrength(100);
        }
    }
    public void getOlder() {
        if(this.age <= 90) {
            setAge(this.age + 10);

        } else {
            setAge(100);
        }
        getStronger();
    }

    public String displayMaturityLevel(){
        String maturity = "";
        AgeType[] values = AgeType.values();
        for (int i = 0; i <= values.length - 1; i++) {
            if(age <= values[i].getAgeThreshold()) {
                maturity = values[i].getHumanREadable();
                return maturity;
            }
        }
        return maturity;
    }

    public void getHungry() {
        setStomach(this.stomach - 10);
        assignHungerLevel();
    }


}


