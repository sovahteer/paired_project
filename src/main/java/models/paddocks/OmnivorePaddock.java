package models.paddocks;

import models.Enums.DinosaurType;

import models.dinosaurs.Omnivore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "omnivore_paddock")
public class OmnivorePaddock extends Paddock{

    private DinosaurType dinoType;

    public OmnivorePaddock(String name, DinosaurType dinoType) {
        super(name);
        this.dinoType = dinoType;
    }

    public OmnivorePaddock(){}

    @Column(name = "dinosaur_type")
    public DinosaurType getDinoType() {
        return dinoType;
    }

    public void setDinoType(DinosaurType dinoType) {
        this.dinoType = dinoType;
    }

    public void createDinosaurInPaddock(){
        Omnivore newOmnivore = new Omnivore(this.dinoType);
        this.dinosaurs.add(newOmnivore);
    }

    public void addDinosaurToPaddock(Omnivore omnivore) {
        this.dinosaurs.add(omnivore);
    }
}
