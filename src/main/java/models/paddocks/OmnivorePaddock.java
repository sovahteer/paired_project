package models.paddocks;

import models.Enums.DinosaurType;
import models.dinosaurs.Omnivore;


public class OmnivorePaddock extends Paddock{

    private DinosaurType dinoType;

    public OmnivorePaddock(DinosaurType dinoType) {
        this.dinoType = dinoType;
    }


    public void addDinosaurToPaddock(DinosaurType dinosaurType){
        if (dinosaurType == this.dinoType){
            Omnivore newOmnivore = new Omnivore(dinosaurType);
            this.dinosaurs.add(newOmnivore);
        }
    }

}
