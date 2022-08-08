package me.tomaszterlecki.travel.model;

import java.util.List;

public class MonthsForAGivenCity {
    private City city;
    private List<MonthsForAGivenYear> months;

    public MonthsForAGivenCity(City city, List<MonthsForAGivenYear> months) {
        this.city = city;
        this.months = months;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<MonthsForAGivenYear> getMonths() {
        return months;
    }

    public void setMonths(List<MonthsForAGivenYear> months) {
        this.months = months;
    }
}
