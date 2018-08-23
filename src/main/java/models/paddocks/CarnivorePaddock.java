package models.paddocks;

import models.Enums.DinosaurType;
import models.dinosaurs.Carnivore;


public class CarnivorePaddock extends Paddock{
    private DinosaurType dinoType;

    public CarnivorePaddock(DinosaurType dinoType) {
        this.dinoType = dinoType;
    }


    public void addDinosaurToPaddock(){
        Carnivore newCarnivore = new Carnivore(this.dinoType);
        this.dinosaurs.add(newCarnivore);
    }

}
