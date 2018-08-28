
import db.DBHelper;
import models.dinosaurs.Dinosaur;
import models.enums.*;

import models.paddocks.Paddock;
import models.visitors.Visit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DinosaurTest {

    Dinosaur trex;
    Dinosaur triceratops;
    Dinosaur velociraptor;
    Dinosaur giganotosaurus;
    Dinosaur brachiosaurus;
    Paddock carnivorePaddock;
    Paddock herbivorePaddock;
    Visit visit;

    @Before
    public void before() {
        trex = new Dinosaur(DinosaurType.TREX);
        triceratops = new Dinosaur(DinosaurType.TRICERATOPS);
        velociraptor = new Dinosaur(DinosaurType.VELOCIRAPTOR);
        giganotosaurus = new Dinosaur(DinosaurType.GIGANOTOSAURUS);
        brachiosaurus = new Dinosaur(DinosaurType.BRACHIOSAURUS);
        carnivorePaddock = new Paddock("Carnivores", DietaryType.CARNIVORE);
        herbivorePaddock = new Paddock("Herbivores", DietaryType.HERBIVORE);
        visit = new Visit();
    }


    @Test
    public void canAddOnlyDinoOfPaddockType() {
        trex.addPaddockToDinosaur(carnivorePaddock);
        assertEquals("Carnivores", trex.getPaddock().getName());
        trex.addPaddockToDinosaur(herbivorePaddock);
        assertEquals("Carnivores", trex.getPaddock().getName());
    }


    @Test
    public void wontAddHerbivoreToCarnivorePaddock(){
        triceratops.addPaddockToDinosaur(carnivorePaddock);
        assertEquals(false, triceratops.checkIfPaddockAssigned());
        triceratops.addPaddockToDinosaur(herbivorePaddock);
        assertEquals(true, triceratops.checkIfPaddockAssigned());
    }

    @Test
    public void wontReassignPaddockToCarnivoreForHerbivore() {
        triceratops.addPaddockToDinosaur(herbivorePaddock);
        assertEquals(DietaryType.HERBIVORE, triceratops.getPaddock().getDietaryType());
        triceratops.addPaddockToDinosaur(carnivorePaddock);
        assertEquals(DietaryType.HERBIVORE, triceratops.getPaddock().getDietaryType());
    }

    @Test
    public void canCheckIfPaddockAssigned() {
        assertEquals(false, trex.checkIfPaddockAssigned());
        trex.addPaddockToDinosaur(carnivorePaddock);
        assertEquals(true, trex.checkIfPaddockAssigned());
    }

    @Test
    public void canAssignHungerLevel() {
        velociraptor.setStomach(10);
        velociraptor.assignHungerLevel();
        assertEquals(HungerLevelType.STARVING, velociraptor.getHungerLevel());
    }

    @Test
    public void hasSpecies(){
        assertEquals(DinosaurType.BRACHIOSAURUS, brachiosaurus.getSpecies());
    }

    @Test
    public void hasFullStomach(){
        assertEquals(100, brachiosaurus.getStomach());
    }

    @Test
    public void wontEatIfFull(){
        assertEquals(100, brachiosaurus.getStomach());
        brachiosaurus.eat(FoodType.LEAVES);
        assertEquals(100, brachiosaurus.getStomach());
    }

    @Test
    public void canGetStronger() {
        assertEquals(10, triceratops.getDefaultStrength());
        triceratops.getStronger();
        assertEquals(13, triceratops.getDefaultStrength());
    }

    @Test
    public void canGetOlder() {
        assertEquals(0, triceratops.getAge());
        triceratops.getOlder();
        assertEquals(10, triceratops.getAge());
    }

    @Test
    public void canGetMaturityLevel() {
        assertEquals(AgeType.HATCHLING.getHumanREadable(), triceratops.displayMaturityLevel());
    }

    @Test
    public void canGetMaturityForAdult(){
        assertEquals(AgeType.HATCHLING.getHumanREadable(), triceratops.displayMaturityLevel());
        triceratops.setAge(55);
        assertEquals(AgeType.ADULT.getHumanREadable(), triceratops.displayMaturityLevel());
    }

    @Test
    public void canGetMaturityForAdolescent(){
        assertEquals(AgeType.HATCHLING.getHumanREadable(), triceratops.displayMaturityLevel());
        triceratops.setAge(49);
        assertEquals(AgeType.ADOLESCENT.getHumanREadable(), triceratops.displayMaturityLevel());
    }

    @Test
    public void canGetStrongerWhenOlder(){
        assertEquals(10, triceratops.getDefaultStrength());
        triceratops.getOlder();
        assertEquals(13, triceratops.getDefaultStrength());
    }



}
