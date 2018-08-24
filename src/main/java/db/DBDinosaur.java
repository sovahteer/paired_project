package db;

import models.dinosaurs.Dinosaur;
import models.enums.DietaryType;
import models.paddocks.Paddock;
import org.hibernate.Criteria;
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
}
