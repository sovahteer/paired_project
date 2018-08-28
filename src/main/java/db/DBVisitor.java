package db;

import models.visitors.Visit;
import models.visitors.Visitor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBVisitor {
    private static Session session;

    public static Visitor findVisitorByUsername(String username) {
        session = HibernateUtil.getSessionFactory().openSession();
        Visitor result = null;
        Criteria cr = session.createCriteria(Visitor.class);
        cr.add(Restrictions.eq(
                "username", username));
        result = (Visitor) cr.uniqueResult();
        return result;
    }
    public static boolean checkIfVisitorByUsernameExists(String username) {
        if(findVisitorByUsername(username) != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Visit getVisitOfVisitor(Visitor visitor) {
        session = HibernateUtil.getSessionFactory().openSession();
        Visit result = null;
        Criteria cr = session.createCriteria(Visit.class);
        cr.add(Restrictions.eq("visitor", visitor));
        result = result;
        return result;
    }

}
