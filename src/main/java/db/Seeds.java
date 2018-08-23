package db;

import models.enums.DinosaurType;
import models.parks.Park;
import models.dinosaurs.Carnivore;
import models.dinosaurs.Herbivore;
import models.paddocks.CarnivorePaddock;
import models.paddocks.Paddock;

public class Seeds {

    public static void seedData() {
        DBHelper.deleteAll(Carnivore.class);
        DBHelper.deleteAll(Herbivore.class);
        DBHelper.deleteAll(CarnivorePaddock.class);
        DBHelper.deleteAll(Park.class);

        Carnivore carnivoreTRex = new Carnivore(DinosaurType.TREX);
        DBHelper.save(carnivoreTRex);
        Herbivore herbivoreTriceratops = new Herbivore(DinosaurType.TRICERATOPS);
        DBHelper.save(herbivoreTriceratops);
        Paddock paddock = new CarnivorePaddock( "T-Rex paddock", DinosaurType.TREX);
        DBHelper.save(paddock);
        Park park = new Park();
        DBHelper.save(park);
    }
}
