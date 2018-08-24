package models.paddocks;

import models.dinosaurs.Dinosaur;
import models.enums.DinosaurType;
import models.parks.Park;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="carnivore_paddock")
public class CarnivorePaddock extends Paddock{
    private DinosaurType dinoType;

    public CarnivorePaddock(String name, Park park, DinosaurType dinoType) {
        super(name, park);
        this.dinoType = dinoType;
    }

    public CarnivorePaddock() {
    }

    @Column(name ="dinosaur_type")
    public DinosaurType getDinoType() {
        return dinoType;
    }

    public void setDinoType(DinosaurType dinoType) {
        this.dinoType = dinoType;
    }

    public void createDinosaurInPaddock(){
        Dinosaur newDinosaur = new Dinosaur(this.dinoType);
            this.dinosaurs.add(newDinosaur);
    }

    public boolean checkIfOfPaddockType(Dinosaur dinosaur) {
        if(dinosaur.getSpecies() == dinoType) {
            return true;
        } else {
            return false;
        }
    }

    public void addDinosaurToPaddock(Dinosaur newDinosaur) {
        DinosaurType dinoType = newDinosaur.getSpecies();
        if(checkIfOfPaddockType(newDinosaur) == true) {
            this.dinosaurs.add(newDinosaur);
        }
    }
}
