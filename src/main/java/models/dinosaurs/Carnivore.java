package models.dinosaurs;

import models.enums.DinosaurType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "carnivores")
public class Carnivore extends Dinosaur {

    public Carnivore(DinosaurType species) {
        super(species);
    }

    public Carnivore() {
    }
}
