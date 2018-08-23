import models.Enums.DinosaurType;
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
        omnivorePaddock = new OmnivorePaddock(DinosaurType.VELOCIRAPTOR);
    }

    @Test
    public void canAddDinosaurs() {
        assertEquals(0, omnivorePaddock.getDinosaurs().size());
        omnivorePaddock.addDinosaurToPaddock(DinosaurType.VELOCIRAPTOR);
        assertEquals(1, omnivorePaddock.getDinosaurs().size());
    }

    @Test
    public void doesntAddDinosaursIfWrongType(){
        omnivorePaddock.addDinosaurToPaddock(DinosaurType.TRICERATOPS);
        assertEquals(0, omnivorePaddock.getDinosaurs().size());
    }
}