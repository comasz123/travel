package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.MonthsForAGivenYear;
import me.tomaszterlecki.travel.model.Picture;

import java.util.List;

public interface IPicturesService {


    public List<MonthsForAGivenYear> yearsAndMonths ();
    public List<City> getAllCitiesForADate (int year, String month);
    List<Picture> getPitcturesByDate(int year, String month, String city);
    List<Picture> getDatesForACity(String cityName);
}
