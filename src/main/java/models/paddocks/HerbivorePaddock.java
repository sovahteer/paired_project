package models.paddocks;

import models.dinosaurs.Dinosaur;
import models.enums.DietryType;
import models.enums.DinosaurType;
import models.parks.Park;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "herbivore_paddock")
public class HerbivorePaddock extends Paddock{

    public HerbivorePaddock(String name, Park park) {
        super(name, park);
    }

    public HerbivorePaddock(){}

    public boolean checkIfHerbivore(Dinosaur dinosaur) {
        if (dinosaur.getSpecies().getDietryType() == DietryType.HERBIVORE) {
            return true;
        } else {
            return false;
        }
    }

    public void createDinosaurInPaddock(DinosaurType dinoType) {
        Dinosaur newDinosaur = new Dinosaur(dinoType);
        if(dinoType.getDietryType() == DietryType.HERBIVORE) {
            this.dinosaurs.add(newDinosaur);
        }

    }

    public void addDinosaurToPaddock(Dinosaur newDinosaur) {
        if (checkIfHerbivore(newDinosaur) == true) {
            this.dinosaurs.add(newDinosaur);
        }
    }

}
