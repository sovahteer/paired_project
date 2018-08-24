package db;

import models.dinosaurs.Dinosaur;
import models.enums.DietryType;
import models.enums.DinosaurType;
import models.paddocks.Paddock;
import models.parks.Park;

public class Seeds {

    public static void seedData() {
        DBHelper.deleteAll(Dinosaur.class);
        DBHelper.deleteAll(Paddock.class);
        DBHelper.deleteAll(Park.class);

        Dinosaur carnivoreTRex = new Dinosaur(DinosaurType.TREX);
        DBHelper.save(carnivoreTRex);
        Dinosaur herbivoreTriceratops = new Dinosaur(DinosaurType.TRICERATOPS);
        DBHelper.save(herbivoreTriceratops);
        Park park = new Park();
        DBHelper.save(park);
        Paddock paddock = new Paddock( "T-Rex paddock", park, DietryType.CARNIVORE);
        DBHelper.save(paddock);

    }
}
