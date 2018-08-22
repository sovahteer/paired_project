package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "omnivores")
public class Omnivore extends Dinosaur {

    public Omnivore(String species, int strength) {
        super(species, strength);
    }

    public Omnivore() {
    }
}
