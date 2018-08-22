package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "herbivores")
public class Herbivore extends Dinosaur {

    public Herbivore(String species, int strength) {
        super(species, strength);
    }

    public Herbivore() {
    }
}
