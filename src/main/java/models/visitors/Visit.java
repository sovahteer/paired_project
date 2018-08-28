package models.visitors;

import db.DBHelper;
import db.DBPaddock;
import models.dinosaurs.Dinosaur;
import models.paddocks.Paddock;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "visits")
public class Visit {

    private List<Paddock> paddocks;
    private int id;
    private Visitor visitor;

    public Visit(Visitor visitor) {
        this.paddocks = new ArrayList<>();
        this.visitor = visitor;
        assignShuffledPaddocks();
    }

    public Visit() {
    }

//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "visits_paddocks",
        joinColumns = {@JoinColumn(name = "visit_id", nullable = false, updatable = false)},
        inverseJoinColumns = {@JoinColumn(name = "paddock_id", nullable = false, updatable = false)}
        )
    public List<Paddock> getPaddocks() {
        return paddocks;
    }

    public void setPaddocks(List<Paddock> visitedPaddocks) {
        this.paddocks = visitedPaddocks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @ManyToOne
    @JoinColumn(name="ship_id", nullable=false)
    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public void addPaddockToVisit(Paddock paddock) {
        this.paddocks.add(paddock);
    }

    public void passageOfTime() {
        List<Dinosaur> allDinos = DBHelper.getAll(Dinosaur.class);
        for (Dinosaur dinosaur: allDinos) {
            dinosaur.getOlder();
            dinosaur.getHungry();
            if(dinosaur.getStomach() < 0) {
                dinosaur.setStomach(0);
            }
            DBHelper.update(dinosaur);
        }
    }
//take paddockst available for visits, limit to 8 or less depending on side
    private static List<Paddock> listOfVisitablePaddocks(){
        List<Paddock> allowedToVisit = DBPaddock.filterByCanVisit();
        List<Paddock> paddocks;
        if (allowedToVisit.size() < 8){
            paddocks = allowedToVisit.subList(0, allowedToVisit.size());
        } else {
            paddocks = allowedToVisit.subList(0, 8);
        }
        Collections.shuffle(paddocks);
        return paddocks;
    }



    public void assignShuffledPaddocks(){
        setPaddocks(listOfVisitablePaddocks());
    }
}
