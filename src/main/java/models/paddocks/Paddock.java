package models.paddocks;

import models.enums.DietryType;
import models.enums.DinosaurType;
import models.parks.Park;
import models.dinosaurs.Dinosaur;

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
    private Park park;
    private DietryType dietryType;
    private DinosaurType dinosaurType;

    public Paddock(){}

    public Paddock(String name, Park park, DietryType dietryType) {
        this.dinosaurs = new ArrayList<>();
        this.dietryType = dietryType;
        this.park = park;
        this.name = name;
        this.dinosaurType = null;
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

    @OneToMany(mappedBy = "paddock", fetch = FetchType.LAZY)
    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public void setDinosaurs(List<Dinosaur> dinosaurs) {
        this.dinosaurs = dinosaurs;
    }

    @ManyToOne
    @JoinColumn(name = "park_id", nullable = true)
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "dietry_type")
    public DietryType getDietryType() {
        return dietryType;
    }


    public void setDietryType(DietryType dietryType) {
        this.dietryType = dietryType;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "dinosaur_type")
    public DinosaurType getDinosaurType() {
        return dinosaurType;
    }

    public void setDinosaurType(DinosaurType dinosaurType) {
        this.dinosaurType = dinosaurType;
    }

    public boolean checkIfOfPaddockType(Dinosaur dinosaur) {
        if(dinosaur.getSpecies() == dinosaurType) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfHerbivore(Dinosaur dinosaur) {
        if(dinosaur.getSpecies().getDietryType() == DietryType.HERBIVORE) {
            return true;
        } else {
            return false;
        }
    }







}
