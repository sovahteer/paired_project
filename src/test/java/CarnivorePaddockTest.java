
import models.Enums.DinosaurType;
import models.paddocks.CarnivorePaddock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarnivorePaddockTest {
    private CarnivorePaddock carnivorePaddock;
    @Before
    public void before() {

        carnivorePaddock = new CarnivorePaddock(DinosaurType.TREX);
    }

    @Test
    public void canAddDinosaurs() {
        assertEquals(0, carnivorePaddock.getDinosaurs().size());
        carnivorePaddock.createDinosaurInPaddock();
        assertEquals(1, carnivorePaddock.getDinosaurs().size());
    }

}