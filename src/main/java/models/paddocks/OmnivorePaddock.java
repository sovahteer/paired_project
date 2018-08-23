package models.paddocks;

import models.Enums.DinosaurType;
import models.dinosaurs.Omnivore;


public class OmnivorePaddock extends Paddock{

    private DinosaurType dinoType;

    public OmnivorePaddock(DinosaurType dinoType) {
        this.dinoType = dinoType;
    }


    public void createDinosaurInPaddock(){
        Omnivore newOmnivore = new Omnivore(this.dinoType);
        this.dinosaurs.add(newOmnivore);
    }

}
