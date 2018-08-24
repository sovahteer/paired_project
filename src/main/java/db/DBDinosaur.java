package db;

import models.dinosaurs.Dinosaur;
import models.paddocks.Paddock;

public class DBDinosaur {

    public static void addPaddockToDinosaur(Dinosaur dinosaur, Paddock paddock) {
        paddock.addDinosaurToPaddock(dinosaur);
        DBHelper.update(paddock);
    }
}
