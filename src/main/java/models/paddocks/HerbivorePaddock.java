package models.paddocks;

import models.Enums.DinosaurType;
import models.dinosaurs.Herbivore;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "herbivore_paddock")
public class HerbivorePaddock extends Paddock{

    public HerbivorePaddock(String name) {
        super(name);
    }

    public HerbivorePaddock(){}

    public void createDinosaurInPaddock(DinosaurType dinoType) {
        Herbivore newHerbivore = new Herbivore(dinoType);
        this.dinosaurs.add(newHerbivore);
    }

    public void addDinosaurToPaddock(Herbivore herbivore) {
        this.dinosaurs.add(herbivore);
    }

}
