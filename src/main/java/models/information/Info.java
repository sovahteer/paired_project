package models.information;

import db.DBHelper;
import db.DBInformation;
import models.dinosaurs.Dinosaur;
import models.enums.DietaryType;
import models.enums.DinosaurType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static Info getRandomInfoOfSpecies(DinosaurType species) {
        List<Info> info = DBInformation.getAllInfoBySpecies(species);
        Collections.shuffle(info);
        Info randomInfo = info.get(0);
        return randomInfo;
    }

    public static Info getRandomInfoOnHerbivore() {
        List<Info> allInfo = DBHelper.getAll(Info.class);
        List<Info> infoByDiet = new ArrayList<>();
        for (Info info: allInfo) {
            if(info.species.getDietaryType() == DietaryType.HERBIVORE) {
                infoByDiet.add(info);
            }
        }
        Collections.shuffle(infoByDiet);
        Info randomInfo = infoByDiet.get(0);
        return randomInfo;
    }
}
