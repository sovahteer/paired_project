package models.dinosaurs;

import models.enums.DinosaurType;

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
