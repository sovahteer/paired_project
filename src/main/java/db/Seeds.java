package db;

import models.dinosaurs.Carnivore;
import models.dinosaurs.Herbivore;

public class Seeds {

    public static void main(String[] args) {
        Carnivore carnivoreTRex = new Carnivore("T-Rex", 300);
        DBHelper.save(carnivoreTRex);
        Herbivore herbivoreTriceratops = new Herbivore("Triceratops", 200);
        DBHelper.save(herbivoreTriceratops);
    }
}
