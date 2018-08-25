package db;

import models.paddocks.Paddock;
import models.visitors.Visit;

import org.hibernate.Session;

public class DBVisit {
    private Session session;

    public static void addPaddockToVisit(Visit visit, Paddock paddock) {
        visit.addPaddockToVisit(paddock);
        paddock.addVisitToPaddock(visit);
        DBHelper.update(visit);
    }
}
