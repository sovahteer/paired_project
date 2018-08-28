import models.dinosaurs.Dinosaur;
import models.enums.DietaryType;
import models.enums.DinosaurType;
import models.paddocks.Paddock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaddockTest {

    private Dinosaur trex;
    private Dinosaur triceratops;
    private Dinosaur velociraptor;
    private Dinosaur giganotosaurus;
    private Paddock carnivorePaddock;
    private Paddock herbivorePaddock;

    @Before
    public void before() {
        trex = new Dinosaur(DinosaurType.TREX);
        triceratops = new Dinosaur(DinosaurType.TRICERATOPS);
        velociraptor = new Dinosaur(DinosaurType.VELOCIRAPTOR);
        giganotosaurus = new Dinosaur(DinosaurType.GIGANOTOSAURUS);
        carnivorePaddock = new Paddock("Carnivores", DietaryType.CARNIVORE);
        herbivorePaddock = new Paddock("Herbivores", DietaryType.HERBIVORE);

    }

    @Test
    public void canCheckIfDinosaurTypeAssigned() {
        carnivorePaddock.setDinosaurType(DinosaurType.GIGANOTOSAURUS);
        assertEquals(true, carnivorePaddock.checkIfDinosaurTypeAssigned());
    }

    @Test
    public void canAssignDinoTypeToPaddock(){
        assertEquals(null, carnivorePaddock.getDinosaurType());
        trex.addPaddockToDinosaur(carnivorePaddock);
        assertEquals(DinosaurType.TREX, carnivorePaddock.getDinosaurType());
        giganotosaurus.addPaddockToDinosaur(carnivorePaddock);
        assertEquals(DinosaurType.TREX, carnivorePaddock.getDinosaurType());
        assertEquals(1, carnivorePaddock.getDinosaurs().size());
    }

}
