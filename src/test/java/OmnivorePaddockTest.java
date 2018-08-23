import models.enums.DinosaurType;
import models.dinosaurs.Omnivore;
import models.paddocks.OmnivorePaddock;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.assertEquals;

public class OmnivorePaddockTest {

    private Omnivore velociraptor;
    private OmnivorePaddock omnivorePaddock;
    @Before
    public void before() {
        velociraptor = new Omnivore(DinosaurType.VELOCIRAPTOR);
        omnivorePaddock = new OmnivorePaddock("Omnivores enclosure", DinosaurType.VELOCIRAPTOR);
    }

    @Test
    public void canCreateDinosaurs() {
        assertEquals(0, omnivorePaddock.getDinosaurs().size());
        omnivorePaddock.createDinosaurInPaddock();
        assertEquals(1, omnivorePaddock.getDinosaurs().size());
    }

    @Test
    public void canAddDinosaur() {
        omnivorePaddock.addDinosaurToPaddock(velociraptor);
        assertEquals(1, omnivorePaddock.getDinosaurs().size());
    }
}
