package models.paddocks;

import models.Enums.DinosaurType;
import models.dinosaurs.Carnivore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="carnivore_paddock")
public class CarnivorePaddock extends Paddock{
    private DinosaurType dinoType;

    public CarnivorePaddock(String name, DinosaurType dinoType) {
        super(name);
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
        Carnivore newCarnivore = new Carnivore(this.dinoType);
        this.dinosaurs.add(newCarnivore);
    }

}
