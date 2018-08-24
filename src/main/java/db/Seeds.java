package db;

import models.dinosaurs.Dinosaur;
import models.enums.DinosaurType;
import models.parks.Park;
import models.paddocks.CarnivorePaddock;
import models.paddocks.Paddock;

public class Seeds {

    public static void seedData() {
        DBHelper.deleteAll(Dinosaur.class);
        DBHelper.deleteAll(CarnivorePaddock.class);
        DBHelper.deleteAll(Park.class);

        Dinosaur carnivoreTRex = new Dinosaur(DinosaurType.TREX);
        DBHelper.save(carnivoreTRex);
        Dinosaur herbivoreTriceratops = new Dinosaur(DinosaurType.TRICERATOPS);
        DBHelper.save(herbivoreTriceratops);
        Park park = new Park();
        DBHelper.save(park);
        CarnivorePaddock paddock = new CarnivorePaddock( "T-Rex paddock", park, DinosaurType.TREX);
        DBHelper.save(paddock);

    }
}
