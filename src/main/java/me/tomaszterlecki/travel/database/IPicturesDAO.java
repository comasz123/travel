package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.model.MonthsForAGivenYear;
import me.tomaszterlecki.travel.model.database.Picture;

import java.util.List;

public interface IPicturesDAO {
    List<MonthsForAGivenYear> yearsAndMonths ();
    List<City> getAllCitiesForADate(int year, String month);
    List<Picture> getPicturesByUserDateAndCity(int year, String month, City city);
    List<Picture> getPicturesByCity(City city);
    List<MonthsForAGivenYear> getDatesForACity(City cityName);
    boolean checkCity(City city);


}
