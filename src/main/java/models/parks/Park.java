package models.parks;

import models.paddocks.Paddock;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "park")
public class Park {
    private List<Paddock> paddocks;
    private int id;
    private String name;


    public Park(String name) {
        this.name = name;
    }

    public Park() {
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

    @OneToMany(mappedBy = "park", fetch = FetchType.LAZY)
    public List<Paddock> getPaddocks() {
        return paddocks;
    }

    public void setPaddocks(List<Paddock> paddocks) {
        this.paddocks = paddocks;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
