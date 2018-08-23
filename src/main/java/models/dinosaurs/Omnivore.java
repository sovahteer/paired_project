package models.dinosaurs;

import models.enums.DinosaurType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "omnivores")
public class Omnivore extends Dinosaur {

    public Omnivore(DinosaurType species) {
        super(species);
    }

    public Omnivore() {
    }
}
