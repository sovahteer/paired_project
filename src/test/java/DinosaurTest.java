import models.dinosaurs.Dinosaur;
import models.enums.DinosaurType;
import models.enums.FoodType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DinosaurTest {

    Dinosaur dinosaur;


    @Before
    public void before(){
        dinosaur = new Dinosaur(DinosaurType.BRACHIOSAURUS);
    }

    @Test
    public void hasSpecies(){
        assertEquals(DinosaurType.BRACHIOSAURUS, dinosaur.getSpecies());
    }

    @Test
    public void hasFullStomach(){
        assertEquals(100, dinosaur.getStomach());
    }

    @Test
    public void canEat(){
        dinosaur.eat(FoodType.LEAVES);
        assertEquals(110, dinosaur.getStomach());
    }
}
