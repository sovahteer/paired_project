package db;

import models.dinosaurs.Dinosaur;
import models.enums.DietaryType;
import models.enums.DinosaurType;
import models.information.Info;
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
        DBHelper.deleteAll(Visit.class);
        DBHelper.deleteAll(Visitor.class);
        DBHelper.deleteAll(Info.class);

        Park park = new Park("YoloPark");
        DBHelper.save(park);
        Park parkTest = new Park("FunPark");
        DBHelper.save(parkTest);
        Paddock funPaddock = new Paddock( "Test Paddock", parkTest, DietaryType.CARNIVORE);
        DBHelper.save(funPaddock);

        Dinosaur bigDino = new Dinosaur(DinosaurType.GIGANOTOSAURUS);
        DBHelper.save(bigDino);
        DBDinosaur.addPaddockToDinosaur(bigDino, funPaddock);
        DBHelper.update(funPaddock);

        Paddock carnivorePaddock = new Paddock( "T-Rex paddock", park, DietaryType.CARNIVORE);
        DBHelper.save(carnivorePaddock);

        Paddock herbivorePaddock = new Paddock("Herbivores", park, DietaryType.HERBIVORE);
        DBHelper.save(herbivorePaddock);

        Paddock herbivorePaddock2 = new Paddock("Herbivore Park", park, DietaryType.HERBIVORE);
        DBHelper.save(herbivorePaddock2);

        Dinosaur carnivoreTRex = new Dinosaur(DinosaurType.TREX);
        DBHelper.save(carnivoreTRex);
        carnivoreTRex.setStomach(60);
        carnivoreTRex.assignHungerLevel();
        DBHelper.update(carnivoreTRex);
        DBDinosaur.addPaddockToDinosaur(carnivoreTRex, carnivorePaddock);
        DBHelper.update(carnivorePaddock);

        Dinosaur carnivoreTrex2 = new Dinosaur(DinosaurType.TREX);
        DBHelper.save(carnivorePaddock);
        DBDinosaur.addPaddockToDinosaur(carnivoreTRex, carnivorePaddock);

        Dinosaur herbivoreTriceratops = new Dinosaur(DinosaurType.TRICERATOPS);
        DBHelper.save(herbivoreTriceratops);
        DBDinosaur.addPaddockToDinosaur(herbivoreTriceratops, herbivorePaddock);
//
        DBHelper.update(herbivorePaddock);



        Dinosaur herbivoreBroDino = new Dinosaur(DinosaurType.BRACHIOSAURUS);
        DBHelper.save(herbivoreBroDino);
        DBDinosaur.addPaddockToDinosaur(herbivoreBroDino, herbivorePaddock);


        List<Paddock> herbivorePaddocksFound = DBDinosaur.getAllPaddocksByDietaryType(DietaryType.HERBIVORE);

        Visitor visitorMike = new Visitor("Mike", "Thorpe", "mikey", "mike@mike.com");
        DBHelper.save(visitorMike);
        Visit visit = new Visit(visitorMike);
        DBHelper.save(visit);
        DBVisit.addPaddockToVisit(visit, carnivorePaddock);


        List<Paddock> allPaddockss = DBPark.getAllPaddocksForPark(park);
        List<Dinosaur> allDino = DBDinosaur.getAllDinoForPaddock(carnivorePaddock);
        List<Dinosaur> allFromPark = DBPark.getAllDinoForPark(park);

        Visitor foundVisitor = DBVisitor.findVisitorByUsername("mikey");
        boolean visitorExistence = DBVisitor.checkIfVisitorByUsernameExists("mikey");
        boolean visitorExistenceFalse = DBVisitor.checkIfVisitorByUsernameExists("jolo");

        visit.passageOfTime(park);
        visit.passageOfTime(park);
        visit.passageOfTime(park);
        visit.passageOfTime(park);
        visit.passageOfTime(park);
        visit.passageOfTime(park);
        visit.passageOfTime(park);
        visit.passageOfTime(park);
        double averageHunger = DBPark.getAverageStomachLevelByPaddock(herbivorePaddock);
        double averageStrength = DBPark.getAverageStrengthByPaddock(herbivorePaddock);
        carnivorePaddock.rampageCheck();
        herbivorePaddock.rampageCheck();

        Info infoTrex = new Info(DinosaurType.TREX);
        DBHelper.save(infoTrex);
        infoTrex.setText("The Tyrannosaurus rex was one of the largest of the land predator dinosaurs. The T-rex measured up to 43 feet long and weighed as much as 7.5 tons. The dinosaur is often used in movie and films such as Jurassic Park due to its size and overall fearsome image. ");
        DBHelper.update(infoTrex);
        Info infoTrex2 = new Info(DinosaurType.TREX);
        DBHelper.save(infoTrex);
        infoTrex2.setText("The Tyrannosaurus had a life span of around 30 years.");
        DBHelper.update(infoTrex2);
        Info infoTrex3 = new Info(DinosaurType.TREX);
        DBHelper.save(infoTrex3);
        infoTrex3.setText("The Tyrannosaurus' arms were too short to reach its mouth.");
        DBHelper.update(infoTrex3);


    }
}
