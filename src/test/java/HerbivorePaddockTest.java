
import models.Enums.DinosaurType;

import models.dinosaurs.Herbivore;
import models.paddocks.HerbivorePaddock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HerbivorePaddockTest {
    private Herbivore triceratops;
    private HerbivorePaddock herbivorePaddock;
    @Before
    public void before() {
        triceratops = new Herbivore(DinosaurType.TRICERATOPS);
        herbivorePaddock = new HerbivorePaddock();
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
