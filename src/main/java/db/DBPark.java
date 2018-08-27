package db;

import models.dinosaurs.Dinosaur;
import models.paddocks.Paddock;
import models.parks.Park;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class DBPark {

    private static Session session;

    public static List<Paddock> getAllPaddocksForPark(Park park){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Paddock> results = null;
        try{
            Criteria cr = session.createCriteria(Paddock.class);
            cr.add(Restrictions.eq("park", park));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }


    public static List<Dinosaur> getAllDinoForPark(Park park){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Dinosaur> results = null;

        try{
            Criteria dinosaurCriteria = session.createCriteria(Dinosaur.class).createAlias("paddock", "paddock").createAlias("paddock.park", "park").add(Restrictions.eq("park.id", park.getId()));
            dinosaurCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            results = dinosaurCriteria.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }

    public static Double getAverageStomachLevelByPaddock(Paddock paddock) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Dinosaur.class);
        cr.add(Restrictions.eq("paddock", paddock));
        cr.setProjection(Projections.avg("stomach"));
        Double averageStomachLevel = (Double) cr.uniqueResult();
        return averageStomachLevel;
    }

    public static Double getAverageStrengthByPaddock(Paddock paddock) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Dinosaur.class);
        cr.add(Restrictions.eq("paddock", paddock));
        cr.setProjection(Projections.avg("defaultStrength"));
        Double averageStrength = (Double) cr.uniqueResult();
        return averageStrength;
    }
}
