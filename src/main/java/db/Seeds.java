package db;

import models.dinosaurs.Dinosaur;
import models.enums.DietaryType;
import models.enums.DinosaurType;
import models.paddocks.Paddock;
import models.parks.Park;

public class Seeds {

    public static void seedData() {
        DBHelper.deleteAll(Dinosaur.class);
        DBHelper.deleteAll(Paddock.class);
        DBHelper.deleteAll(Park.class);

        Park park = new Park("YoloPark");
        DBHelper.save(park);

        Paddock carnivorePaddock = new Paddock( "T-Rex paddock", park, DietaryType.CARNIVORE);
        DBHelper.save(carnivorePaddock);

        Paddock herbivorePaddock = new Paddock("Herbivores", park, DietaryType.HERBIVORE);
        DBHelper.save(herbivorePaddock);

        Dinosaur carnivoreTRex = new Dinosaur(DinosaurType.TREX);
        DBHelper.save(carnivoreTRex);
        DBDinosaur.addPaddockToDinosaur(carnivoreTRex, carnivorePaddock);

        Dinosaur herbivoreTriceratops = new Dinosaur(DinosaurType.TRICERATOPS);
        DBHelper.save(herbivoreTriceratops);
        DBDinosaur.addPaddockToDinosaur(herbivoreTriceratops, herbivorePaddock);



    }
}
