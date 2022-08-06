package me.tomaszterlecki.travel.model;

import javax.persistence.*;

@Entity(name = "tpicture")
public class Picture implements Writeable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Orientation orientation;
    private String fileName;
    private int year;
    @ManyToOne(fetch = FetchType.EAGER)
    private Month month;
    @ManyToOne(fetch = FetchType.EAGER)
    private City city;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    public enum Orientation {
        HORIZONTAL,
        VIRTUAL,
    }
}
