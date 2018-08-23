package models.paddocks;

import models.dinosaurs.Dinosaur;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "paddock")
public abstract class Paddock {

    protected List<Dinosaur> dinosaurs;
    private int id;
    private String name;


    public Paddock(){}

    public Paddock(String name) {
        this.dinosaurs = new ArrayList<>();
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
        return name;
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


}
