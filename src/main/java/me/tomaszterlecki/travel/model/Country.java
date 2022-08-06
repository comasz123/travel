package me.tomaszterlecki.travel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tcountry")
public class Country implements Writeable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameEng;
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
