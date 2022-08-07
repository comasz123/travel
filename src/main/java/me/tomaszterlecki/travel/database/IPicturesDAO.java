package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.MonthsForAGivenYear;
import me.tomaszterlecki.travel.model.Picture;

import java.util.List;

public interface IPicturesDAO {
    int[] getYearsByCity(City city);
    int[] extractYears();
    String[] getMonthsForAGivenYear(int year);
    List<MonthsForAGivenYear> yearsAndMonths ();
    List<City> getAllCitiesForADate(int year, String month);
    List<Picture> getPitcturesByDate(int year, String month, String city);

}
