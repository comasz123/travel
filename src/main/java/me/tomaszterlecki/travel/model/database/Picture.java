package me.tomaszterlecki.travel.model.database;

import me.tomaszterlecki.travel.model.IWriteable;

import javax.persistence.*;

@Entity(name = "tpicture")
public class Picture implements IWriteable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Orientation orientation;
    @Column(unique = true)
    private String fileName;
    private int year;
    @ManyToOne
    private Month month;
    @ManyToOne
    private City city;
    @ManyToOne
    private User user;
    public enum Orientation {
        HORIZONTAL,
        VERTICAL,
    }

    public Picture() {
    }

    public Picture(int id, String description, Orientation orientation, String fileName, int year, Month month, City city, User user) {
        this.id = id;
        this.description = description;
        this.orientation = orientation;
        this.fileName = fileName;
        this.year = year;
        this.month = month;
        this.city = city;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
