package me.tomaszterlecki.travel.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tcountry")
public class Country implements Writeable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String nameEng;
    @Column(unique = true)
    private String namePol;

    public Country() {
    }

    public Country(int id, String nameEng, String namePol) {
        this.id = id;
        this.nameEng = nameEng;
        this.namePol = namePol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNamePol() {
        return namePol;
    }

    public void setNamePol(String namePol) {
        this.namePol = namePol;
    }
}
