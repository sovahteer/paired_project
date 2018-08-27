package db;

import models.dinosaurs.Dinosaur;
import models.enums.DietaryType;
import models.enums.HungerLevelType;
import models.paddocks.Paddock;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBDinosaur {

    private static Session session;

    public static void addPaddockToDinosaur(Dinosaur dinosaur, Paddock paddock) {
        dinosaur.addPaddockToDinosaur(paddock);
        DBHelper.update(dinosaur);
    }

    public static List<Paddock> getAllPaddocksByDietaryType(DietaryType dietaryType) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Paddock> results;
        Criteria cr = session.createCriteria(Paddock.class);
        cr.add(Restrictions.eq("dietaryType", dietaryType));
        results = cr.list();
        return results;
    }

    public static List<Dinosaur> getAllDinoForPaddock(Paddock paddock){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Dinosaur> results = null;
        try{
            Criteria cr = session.createCriteria(Dinosaur.class);
            cr.add(Restrictions.eq("paddock", paddock));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }


    public static HungerLevelType checkHungerLevel(int value) {

        HungerLevelType hungerLevel = null;
        if (value <= 20) {
            hungerLevel = HungerLevelType.STARVING;
        } else if (value > 20 && value <= 50) {
            hungerLevel = HungerLevelType.HUNGRY;
        } else if (value > 50 && value <= 80) {
            hungerLevel = HungerLevelType.FED;
        } else if (value > 80) {
            hungerLevel = HungerLevelType.FULL;
        }
        return hungerLevel;
    }
}
