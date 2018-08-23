package models.dinosaurs;

import models.Enums.DinosaurType;
import models.dinosaurs.Dinosaur;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "herbivores")
public class Herbivore extends Dinosaur {

    public Herbivore(DinosaurType species) {
        super(species);
    }

    public Herbivore() {
    }

}
