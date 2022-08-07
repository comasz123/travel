package me.tomaszterlecki.travel.database.hibernate;

import me.tomaszterlecki.travel.database.ICityDAO;
import me.tomaszterlecki.travel.database.IMonthDAO;
import me.tomaszterlecki.travel.database.IPicturesDAO;
import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Month;
import me.tomaszterlecki.travel.model.MonthsForAGivenYear;
import me.tomaszterlecki.travel.model.Picture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class PictureDAOImpl implements IPicturesDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    IMonthDAO monthDAO;
    @Autowired
    ICityDAO cityDAO;

    @Override
    public int[] getYearsByCity(City city) {
        Session session = sessionFactory.openSession();
        Query<Picture> query = session.createQuery
                ("FROM me.tomaszterlecki.travel.model.Picture WHERE city =:city");
        query.setParameter("city", city);
        List <Picture> pictures = query.getResultList();
        session.close();
        int[] result = getYears(pictures);
        return result;
    }
    @Override
    public List<MonthsForAGivenYear> yearsAndMonths (){
        List<MonthsForAGivenYear> result = new ArrayList<>();
        int[] years = extractYears();
        for (int i = 0; i < years.length; i++) {
            String[] months = getMonthsForAGivenYear(years[i]);
            MonthsForAGivenYear item = new MonthsForAGivenYear(years[i],months);
            result.add(item);
        }
        return result;
    }
    @Override
    public int[] extractYears() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM me.tomaszterlecki.travel.model.Picture");
        List<Picture> allPictures = query.getResultList();
        int[] result = getYears(allPictures);
        session.close();
        Arrays.sort(result);
        return result;
    }
    @Override
    public List<City> getAllCitiesForADate(int year, String monthString){
        Month monthObject = monthDAO.getMonthByNameEng(monthString);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM me.tomaszterlecki.travel.model.Picture WHERE year=:year AND month=:month");
        query.setParameter("year", year);
        query.setParameter("month", monthObject);
        List<Picture> resultPictures = query.getResultList();
        session.close();

        Set<City> resultSet = new HashSet<>();
        for (Picture picture : resultPictures ) {
            resultSet.add(picture.getCity());
        }
        List<City> result = new ArrayList<>();
        for (City city : resultSet) {
            result.add(city);
        }
        return result;
    }
    @Override
    public List<Picture> getPitcturesByDate(int year, String monthString, String cityString){
        Month monthObject = monthDAO.getMonthByNameEng(monthString);
        City city = cityDAO.getCityByName(cityString);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM me.tomaszterlecki.travel.model.Picture WHERE " +
                "year=:year AND month=:month AND city=:city");
        query.setParameter("year", year);
        query.setParameter("month", monthObject);
        query.setParameter("city", city);
        List<Picture> resultPictures = query.getResultList();
        session.close();
        return resultPictures;
    }
    public String[] getMonthsForAGivenYear(int year){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM me.tomaszterlecki.travel.model.Picture WHERE year=:year");
        query.setParameter("year", year);
        List<Picture> pictures = query.getResultList();
        session.close();
        String[] result = getMonthsEng(pictures);
        return result;
    }

    private String[] getMonthsEng(List<Picture> pictures){
        Set<String> monthsSet = new HashSet<>();
        for(Picture picture: pictures){
            monthsSet.add(picture.getMonth().getNameEng());
        }
        int list = monthsSet.size();
        String [] result = new String[list];
        int i = 0;
        for (String month : monthsSet) {
            result[i]=month;
            i++;
        }
        return result;

    }
    private int[] getYears(List<Picture> pictures){
        Set<Integer> yearSet = new HashSet<>();
        for (Picture picture : pictures) {
            yearSet.add(picture.getYear());
        }

        int list = yearSet.size();
        int [] result = new int[list];
        int i = 0;
        for (int year : yearSet) {
            result[i]=year;
            i++;
        }
        return result;
    }

}
