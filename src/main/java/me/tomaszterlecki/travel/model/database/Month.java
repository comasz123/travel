package me.tomaszterlecki.travel.model.database;

import me.tomaszterlecki.travel.model.IWriteable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tmonth")
public class Month implements IWriteable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameEng;
    private String namePol;

    public Month() {
    }

    public Month(int id, String nameEng, String namePol) {
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
