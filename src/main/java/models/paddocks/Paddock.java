package models.paddocks;

import models.enums.DietaryType;
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
    private DietaryType dietaryType;
    private DinosaurType dinosaurType;

    public Paddock(){}

    public Paddock(String name, Park park, DietaryType dietaryType) {
        this.dinosaurs = new ArrayList<>();
        this.dietaryType = dietaryType;
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

    @OneToMany(mappedBy = "paddock", fetch = FetchType.EAGER)
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





}
