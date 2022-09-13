package me.tomaszterlecki.travel.session;

import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.MonthsForAGivenYear;
import me.tomaszterlecki.travel.model.database.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class SessionObject {
    private User user = null;
    private List<MonthsForAGivenYear> yearsTravelled = new ArrayList<>();
    private List<CitiesForAGivenCountry> citiesForAGivenCountries = new ArrayList<>();

    public SessionObject() {
    }

    public SessionObject(User user, List<MonthsForAGivenYear> yearsTravelled, List<CitiesForAGivenCountry> citiesForAGivenCountries) {
        this.user = user;
        this.yearsTravelled = yearsTravelled;
        this.citiesForAGivenCountries = citiesForAGivenCountries;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MonthsForAGivenYear> getYearsTravelled() {
        return yearsTravelled;
    }

    public void setYearsTravelled(List<MonthsForAGivenYear> yearsTravelled) {
        this.yearsTravelled = yearsTravelled;
    }

    public List<CitiesForAGivenCountry> getCitiesForAGivenCountries() {
        return citiesForAGivenCountries;
    }

    public void setCitiesForAGivenCountries(List<CitiesForAGivenCountry> citiesForAGivenCountries) {
        this.citiesForAGivenCountries = citiesForAGivenCountries;
    }

    public boolean isLogged() {
        return this.user != null;
    }
}
