package models.paddocks;

import models.Enums.DinosaurType;
import models.dinosaurs.Herbivore;

public class HerbivorePaddock extends Paddock{

    public HerbivorePaddock() {
    }

    public void createDinosaurInPaddock(DinosaurType dinoType) {
        Herbivore newHerbivore = new Herbivore(dinoType);
        this.dinosaurs.add(newHerbivore);
    }

    public void addDinosaurToPaddock(Herbivore herbivore) {
        this.dinosaurs.add(herbivore);
    }

}
