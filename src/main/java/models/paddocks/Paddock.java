package models.paddocks;

import db.DBDinosaur;
import db.DBHelper;
import db.DBPaddock;
import models.enums.DietaryType;
import models.enums.DinosaurType;
import models.enums.HungerLevelType;
import models.dinosaurs.Dinosaur;
import models.visitors.Visit;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "paddock")
public class Paddock {

    protected List<Dinosaur> dinosaurs;
    private int id;
    private String name;
    private DietaryType dietaryType;
    private DinosaurType dinosaurType;
    private List<Visit> visits;
    private boolean inRampage;
    private boolean accessibleToVisitors;

    public Paddock(){}

    public Paddock(String name, DietaryType dietaryType) {
        this.dinosaurs = new ArrayList<>();
        this.dietaryType = dietaryType;
        this.name = name;
        this.dinosaurType = null;
        this.visits = new ArrayList<>();
        this.inRampage = false;
        this.accessibleToVisitors = false;
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

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "paddock", fetch = FetchType.EAGER)
    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public void setDinosaurs(List<Dinosaur> dinosaurs) {
        this.dinosaurs = dinosaurs;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "dietary_type")
    public DietaryType getDietaryType() {
        return dietaryType;
    }


    public void setDietaryType(DietaryType dietaryType) {
        this.dietaryType = dietaryType;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "dinosaur_type")
    public DinosaurType getDinosaurType() {
        return dinosaurType;
    }

    public void setDinosaurType(DinosaurType dinosaurType) {
        this.dinosaurType = dinosaurType;
    }

//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "visits_paddocks",
            joinColumns = {@JoinColumn(name = "paddock_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "visit_id", nullable = false, updatable = false)}
    )
    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    @Column(name="in_rampage")
    public boolean isInRampage() {
        return inRampage;
    }

    public void setInRampage(boolean inRampage) {
        this.inRampage = inRampage;
    }


    @Column(name="accessibility_to_visitors")
    public boolean isAccessibleToVisitors() {
        return accessibleToVisitors;
    }

    public void setAccessibleToVisitors(boolean accessibleToVisitors) {
        this.accessibleToVisitors = accessibleToVisitors;
    }

    public void makeAvailableForVisits() {
        setAccessibleToVisitors(true);
    }

    public void makeNotAvailableForVisit() {
        setAccessibleToVisitors(false);
    }

    public boolean checkIfOfPaddockType(Dinosaur dinosaur) {
        if(dinosaur.getSpecies() == dinosaurType) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfDinosaurTypeAssigned() {
        if(this.dinosaurType != null) {
            return true;
        }else {
            return false;
        }
    }

    public boolean checkIfHerbivore(Dinosaur dinosaur) {
        if(dinosaur.getSpecies().getDietaryType() == DietaryType.HERBIVORE) {
            return true;
        } else {
            return false;
        }
    }


    public void assignDinosaurType(DinosaurType dinosaurType) {
        if(!checkIfDinosaurTypeAssigned()) {
            this.setDinosaurType(dinosaurType);
        }
    }

    public void addDinoToPaddock(Dinosaur dinosaur) {
        this.dinosaurs.add(dinosaur);
    }

    public void addVisitToPaddock(Visit visit) {
        this.visits.add(visit);
    }

    public void rampageCheck() {
        Double averageStomachLevel = DBPaddock.getAverageStomachLevelByPaddock(this);
        int roundedStomachLevel =  (int) Math.round(averageStomachLevel);
        HungerLevelType hungerLevel = DBDinosaur.checkHungerLevel(roundedStomachLevel);
        Double averageStrength = DBPaddock.getAverageStrengthByPaddock(this);
        int roundedStrengthLevel = (int) Math.round(averageStrength);
        if(hungerLevel == HungerLevelType.STARVING && roundedStrengthLevel > 30) {
            this.setInRampage(true);
            DBHelper.update(this);
        }
    }

    public void restoreOrder() {
        setInRampage(false);
    }

}
