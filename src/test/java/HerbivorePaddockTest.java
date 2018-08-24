
import models.dinosaurs.Dinosaur;
import models.enums.DinosaurType;

import models.paddocks.HerbivorePaddock;
import models.parks.Park;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HerbivorePaddockTest {
    private Dinosaur triceratops;
    private Park park;
    private HerbivorePaddock herbivorePaddock;
    @Before
    public void before() {
        park = new Park();
        triceratops = new Dinosaur(DinosaurType.TRICERATOPS);
        herbivorePaddock = new HerbivorePaddock("Herbivores", park);
    }

    @Test
    public void canCreateDinosaurs() {
        assertEquals(0, herbivorePaddock.getDinosaurs().size());
        herbivorePaddock.createDinosaurInPaddock(DinosaurType.TRICERATOPS);
        assertEquals(1, herbivorePaddock.getDinosaurs().size());
    }

    @Test
    public void canAddDinosaurs() {
        herbivorePaddock.addDinosaurToPaddock(triceratops);
        assertEquals(1, herbivorePaddock.getDinosaurs().size());
    }

}
