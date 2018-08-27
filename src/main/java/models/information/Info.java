package models.information;

import models.dinosaurs.Dinosaur;
import models.enums.DinosaurType;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="information")
public class Info {

    private DinosaurType species;
    private String text;
    private int id;

    public Info(DinosaurType species) {
        this.species = species;
    }

    public Info() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name="species")
    public DinosaurType getSpecies() {
        return species;
    }

    public void setSpecies(DinosaurType species) {
        this.species = species;
    }

    @Column(name="text", length = 65535, columnDefinition="Text")
    @Type(type = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
