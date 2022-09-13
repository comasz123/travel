package me.tomaszterlecki.travel.model.database;
import me.tomaszterlecki.travel.model.IWriteable;

import javax.persistence.*;

@Entity(name = "tuser")
public class User implements IWriteable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, unique = true)
    private String login;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String password;

    @Enumerated(EnumType.STRING)
    Status status;


    public User() {
    }

    public User(int id, String login, String name, String password) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        ADMIN,
        USER,
    }
}
