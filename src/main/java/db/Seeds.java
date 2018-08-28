package db;

import models.dinosaurs.Dinosaur;
import models.enums.DietaryType;
import models.enums.DinosaurType;
import models.information.Info;
import models.paddocks.Paddock;
import models.visitors.Visit;
import models.visitors.Visitor;

import java.util.List;

public class Seeds {

    public static void seedData() {
        DBHelper.deleteAll(Dinosaur.class);
        DBHelper.deleteAll(Paddock.class);
        DBHelper.deleteAll(Visit.class);
        DBHelper.deleteAll(Visitor.class);
        DBHelper.deleteAll(Info.class);



        Paddock funPaddock = new Paddock( "Test Paddock", DietaryType.CARNIVORE);
        DBHelper.save(funPaddock);

        Dinosaur bigDino = new Dinosaur(DinosaurType.GIGANOTOSAURUS);
        DBHelper.save(bigDino);
        DBDinosaur.addPaddockToDinosaur(bigDino, funPaddock);
        DBHelper.update(funPaddock);

        Paddock carnivorePaddock = new Paddock( "T-Rex paddock", DietaryType.CARNIVORE);
        DBHelper.save(carnivorePaddock);

        Paddock herbivorePaddock = new Paddock("Herbivores", DietaryType.HERBIVORE);
        DBHelper.save(herbivorePaddock);

        Paddock herbivorePaddock2 = new Paddock("Herbivore Park", DietaryType.HERBIVORE);
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

        double averageHunger = DBPaddock.getAverageStomachLevelByPaddock(herbivorePaddock);
        double averageStrength = DBPaddock.getAverageStrengthByPaddock(herbivorePaddock);
        carnivorePaddock.rampageCheck();
        herbivorePaddock.rampageCheck();

        Info infoTrex = new Info(DinosaurType.TREX);
        DBHelper.save(infoTrex);
        infoTrex.setText("The Tyrannosaurus rex was one of the largest of the land predator dinosaurs. The T-rex measured up to 43 feet long and weighed as much as 7.5 tons. The dinosaur is often used in movie and films such as Jurassic Park due to its size and overall fearsome image. ");
        DBHelper.update(infoTrex);
        Info infoTrex2 = new Info(DinosaurType.TREX);
        DBHelper.save(infoTrex2);
        infoTrex2.setText("The Tyrannosaurus had a life span of around 30 years.");
        DBHelper.update(infoTrex2);
        Info infoTrex3 = new Info(DinosaurType.TREX);
        DBHelper.save(infoTrex3);
        infoTrex3.setText("The Tyrannosaurus' arms were too short to reach its mouth.");
        Info infoGiganotosaurus = new Info(DinosaurType.GIGANOTOSAURUS);
        DBHelper.save(infoGiganotosaurus);
        infoGiganotosaurus.setText("This is one of the biggest meat eating dinosaur that ever lived. Even bigger than the dinosaur T Rex - but a little bit smaller than the Spinosaurus. Despite its size it had a tiny brain! About the size of a small cucumber. ");
        DBHelper.update(infoGiganotosaurus);
        Info infoGiganotosaurus2 = new Info(DinosaurType.GIGANOTOSAURUS);
        DBHelper.save(infoGiganotosaurus2);
        infoGiganotosaurus2.setText("They are considered to be one of the largest meat eating dinosaur. The bigger males would have weighed over 13 tons. It is estimated that their skulls would have been 6.40 ft in length.");
        DBHelper.update(infoGiganotosaurus2);

        Info infoVelociraptor = new Info(DinosaurType.VELOCIRAPTOR);
        DBHelper.save(infoVelociraptor);
        infoVelociraptor.setText("A fully grown Velociraptor could grow up to 2m (6.6ft) in length, 0.5m (1.6ft) in height at the hip and weigh up to 15kg (33lb). It kills its prey with sickle shaped claws on its rear feet.");
        DBHelper.update(infoVelociraptor);
        Info infoPterosaur = new Info(DinosaurType.PTEROSAUR);
        DBHelper.save(infoPterosaur);
        infoPterosaur.setText("Pterosaur ranged in size from Just a few centimetres to more than 12 metres, mostly feeding on fish while some scavenged dead animals and ate insects inland.");
        DBHelper.update(infoPterosaur);
        Info infoPterosaur2 = new Info(DinosaurType.PTEROSAUR);
        DBHelper.save(infoPterosaur2);
        infoPterosaur2.setText("The wing of the pterosaur was unique, a large membrane suspended from a hugely expanded fourth finger. Pterosaurs are largely regarded as the first vertebrates to achieve sustainable powered flight, although the largest relied mainly on the wind and gliding to stay in the air.");
        DBHelper.update(infoPterosaur2);



        carnivorePaddock.setAccessibleToVisitors(true);
        DBHelper.update(carnivorePaddock);
        herbivorePaddock.setAccessibleToVisitors(true);
        DBHelper.update(herbivorePaddock);
        Visit visit = new Visit(visitorMike);
        DBHelper.save(visit);


        List<Dinosaur> allDino = DBDinosaur.getAllDinoForPaddock(carnivorePaddock);

        Visitor foundVisitor = DBVisitor.findVisitorByUsername("mikey");
        boolean visitorExistence = DBVisitor.checkIfVisitorByUsernameExists("mikey");
        boolean visitorExistenceFalse = DBVisitor.checkIfVisitorByUsernameExists("jolo");

//        visit.passageOfTime();
//        visit.passageOfTime();
//        visit.passageOfTime();
//        visit.passageOfTime();
//        visit.passageOfTime();
//        visit.passageOfTime();
//        visit.passageOfTime();
//        visit.passageOfTime();
        List<Paddock> allowedToVisit = DBPaddock.filterByCanVisit();
        List<Paddock> shuffledPaddocksForVisit = visit.getPaddocks();

    }
}
