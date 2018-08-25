package db;

import models.dinosaurs.Dinosaur;
import models.paddocks.Paddock;
import models.parks.Park;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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



//    Criteria accountCriteria = getCurrentSession().createCriteria(Account.class,"acc");
//    Criteria bookCriteria =  accountCriteria .createCriteria("book","b");
//    Criteria orgCriteria =  bookCriteria.createCriteria("organization","org");
//orgCriteria.add(Restrictions.eq("name", "XYZ"));
//
//    ProjectionList properties = Projections.projectionList();
//properties.add(Projections.property("name"));
//properties.add(Projections.property("id"));
//
//accountCriteria.setProjection(properties);
//accountCriteria.list();


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
}
