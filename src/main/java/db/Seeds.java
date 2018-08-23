package db;

import models.Enums.DinosaurType;
import models.dinosaurs.Carnivore;
import models.dinosaurs.Herbivore;

public class Seeds {

    public static void main(String[] args) {
        Carnivore carnivoreTRex = new Carnivore(DinosaurType.TREX);
        DBHelper.save(carnivoreTRex);
//        Herbivore herbivoreTriceratops = new Herbivore(DinosaurType.TRICERATOPS);
//        DBHelper.save(herbivoreTriceratops);
    }
}
