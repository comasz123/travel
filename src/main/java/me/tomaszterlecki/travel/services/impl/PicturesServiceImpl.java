package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.IPicturesDAO;
import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.MonthsForAGivenYear;
import me.tomaszterlecki.travel.model.Picture;
import me.tomaszterlecki.travel.services.IPicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicturesServiceImpl implements IPicturesService {
    @Autowired
    IPicturesDAO picturesDAO;
    @Override
    public List<MonthsForAGivenYear> yearsAndMonths () {
        List<MonthsForAGivenYear> result = picturesDAO.yearsAndMonths();
        return result;
    }
    public List<City> getAllCitiesForADate (int year, String month){
        List<City> result = picturesDAO.getAllCitiesForADate(year, month);
        return result;
    }
    public List<Picture> getPicturesByDateAndCity(int year, String month, City city){
        List<Picture> result = picturesDAO.getPicturesByUserDateAndCity(year, month, city);
        return result;
    }
    public List<MonthsForAGivenYear> getDatesForACity(City city){
        List<MonthsForAGivenYear> result = picturesDAO.getDatesForACity(city);
       return result;
    }

}
