
import models.dinosaurs.Dinosaur;
import models.enums.DietryType;
import models.enums.DinosaurType;
import models.paddocks.Paddock;
import models.parks.Park;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaddockTest {
    private Dinosaur trex;
    private Dinosaur triceratops;
    private Dinosaur velociraptor;
    private Park park;
    private Paddock carnivorePaddock;
    private Paddock herbivorePaddock;
    @Before
    public void before() {
        trex = new Dinosaur(DinosaurType.TREX);
        triceratops = new Dinosaur(DinosaurType.TRICERATOPS);
        velociraptor = new Dinosaur(DinosaurType.VELOCIRAPTOR);
        park = new Park();
        carnivorePaddock = new Paddock("Carnivores", park, DietryType.CARNIVORE);
        herbivorePaddock = new Paddock("Herbivores", park, DietryType.HERBIVORE);
    }


    @Test
    public void canAddOnlyDinoOfPaddockType() {
        carnivorePaddock.addDinosaurToPaddock(trex);
        assertEquals(1, carnivorePaddock.getDinosaurs().size());
        carnivorePaddock.addDinosaurToPaddock(velociraptor);
        assertEquals(1, carnivorePaddock.getDinosaurs().size());
    }

    @Test
    public void wontAddHerbivore(){
        carnivorePaddock.addDinosaurToPaddock(triceratops);
        assertEquals(0, carnivorePaddock.getDinosaurs().size());
    }

    @Test
    public void wontAddCarnivoreToHerbivorePaddock() {
        herbivorePaddock.addDinosaurToPaddock(trex);
        assertEquals(0, herbivorePaddock.getDinosaurs().size());
    }

    @Test
    public void wontAddHerbivoreToCarnivorePaddock() {
        carnivorePaddock.addDinosaurToPaddock(triceratops);
        assertEquals(0, herbivorePaddock.getDinosaurs().size());
    }
}
