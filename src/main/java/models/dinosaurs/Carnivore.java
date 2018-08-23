package models.dinosaurs;

import models.Enums.DinosaurType;

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
