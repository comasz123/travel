package me.tomaszterlecki.travel.model;

import javax.persistence.*;

@Entity(name = "tcity")
public class City implements Writeable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameEng;

    private String namePol;
    @ManyToOne
    private Country country;

    public City() {
    }

    public City(int id, String nameEng, String namePol, Country country) {
        this.id = id;
        this.nameEng = nameEng;
        this.namePol = namePol;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
