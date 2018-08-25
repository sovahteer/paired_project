package db;

import models.dinosaurs.Dinosaur;
import models.enums.DietaryType;
import models.enums.DinosaurType;
import models.paddocks.Paddock;
import models.parks.Park;
import models.visitors.Visit;
import models.visitors.Visitor;

import java.util.List;

public class Seeds {

    public static void seedData() {
        DBHelper.deleteAll(Dinosaur.class);
        DBHelper.deleteAll(Paddock.class);
        DBHelper.deleteAll(Park.class);

        Park park = new Park("YoloPark");
        DBHelper.save(park);
        Park parkTest = new Park("FunPark");

//        Paddock funPaddock = new Paddock( "Test Paddock", parkTest, DietaryType.CARNIVORE);
//        DBHelper.save(funPaddock);
//
//        Dinosaur bigDino = new Dinosaur(DinosaurType.GIGANOTOSAURUS);
//        DBHelper.save(bigDino);
//
//        DBDinosaur.addPaddockToDinosaur(bigDino, funPaddock);

        Paddock carnivorePaddock = new Paddock( "T-Rex paddock", park, DietaryType.CARNIVORE);
        DBHelper.save(carnivorePaddock);

        Paddock herbivorePaddock = new Paddock("Herbivores", park, DietaryType.HERBIVORE);
        DBHelper.save(herbivorePaddock);

        Paddock herbivorePaddock2 = new Paddock("Herbivore Park", park, DietaryType.HERBIVORE);
        DBHelper.save(herbivorePaddock2);

        Dinosaur carnivoreTRex = new Dinosaur(DinosaurType.TREX);
        DBHelper.save(carnivoreTRex);
        DBDinosaur.addPaddockToDinosaur(carnivoreTRex, carnivorePaddock);
        DBHelper.update(carnivorePaddock);

        Dinosaur herbivoreTriceratops = new Dinosaur(DinosaurType.TRICERATOPS);
        DBHelper.save(herbivoreTriceratops);
        DBDinosaur.addPaddockToDinosaur(herbivoreTriceratops, herbivorePaddock);
//
        DBHelper.update(herbivorePaddock);




        List<Paddock> herbivorePaddocksFound = DBDinosaur.getAllPaddocksByDietaryType(DietaryType.HERBIVORE);

        Visitor visitorMike = new Visitor("Mike");
        DBHelper.save(visitorMike);
        Visit visit = new Visit(visitorMike);
        DBHelper.save(visit);
        DBVisit.addPaddockToVisit(visit, carnivorePaddock);
    }
}
