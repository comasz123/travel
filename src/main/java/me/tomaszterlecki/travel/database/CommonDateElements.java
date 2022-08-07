package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.MonthsForAGivenYear;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class CommonDateElements {
    private List<MonthsForAGivenYear> yearsAndMonths;
    @Autowired
    IPicturesDAO picturesDAO;

    public CommonDateElements(){

    }
    public List<MonthsForAGivenYear> getYearsAndMonths(){
        int[] years = picturesDAO.extractYears();
        for (int i = 0; i < years.length; i++) {
            MonthsForAGivenYear item = new MonthsForAGivenYear(years[i], picturesDAO.getMonthsForAGivenYear(years[i]));
            this.yearsAndMonths.add(item);
        }
        return this.yearsAndMonths;
    }
    public List<MonthsForAGivenYear> getDatesForACity(City city){
        return null;
    }

}
