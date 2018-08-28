package db;

import models.enums.DietaryType;
import models.enums.DinosaurType;
import models.information.Info;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBInformation {
    private static Session session;

    public static List<Info> getAllInfoBySpecies(DinosaurType species) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Info> results = null;
        Criteria cr = session.createCriteria(Info.class);
        cr.add(Restrictions.eq("species", species));
        results = cr.list();
        return results;
    }


}
