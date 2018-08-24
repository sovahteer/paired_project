
import models.dinosaurs.Dinosaur;
import models.enums.DinosaurType;
import models.paddocks.CarnivorePaddock;
import models.parks.Park;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarnivorePaddockTest {
    private Dinosaur trex;
    private Dinosaur triceratops;
    private Dinosaur velociraptor;
    private Park park;
    private CarnivorePaddock carnivorePaddock;
    @Before
    public void before() {
        trex = new Dinosaur(DinosaurType.TREX);
        triceratops = new Dinosaur(DinosaurType.TRICERATOPS);
        velociraptor = new Dinosaur(DinosaurType.VELOCIRAPTOR);
        park = new Park();
        carnivorePaddock = new CarnivorePaddock("Carnivores", park, DinosaurType.TREX);
    }

    @Test
    public void canCreateDinosaurs() {
        assertEquals(0, carnivorePaddock.getDinosaurs().size());
        carnivorePaddock.createDinosaurInPaddock();
        assertEquals(1, carnivorePaddock.getDinosaurs().size());
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
}
