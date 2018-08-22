package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "carnivores")
public class Carnivore extends Dinosaur {

    public Carnivore(String species, int strength) {
        super(species, strength);
    }

    public Carnivore() {
    }
}
