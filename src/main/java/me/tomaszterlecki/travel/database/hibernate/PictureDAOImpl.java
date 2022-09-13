package me.tomaszterlecki.travel.database.hibernate;

import me.tomaszterlecki.travel.database.*;
import me.tomaszterlecki.travel.model.*;
import me.tomaszterlecki.travel.model.database.*;
import me.tomaszterlecki.travel.session.SessionObject;
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
    @Autowired
    ICountryDAO countryDAO;
    @Autowired
    IUserDAO userDAO;
    @Autowired
    SessionObject sessionObject;

    public List<MonthsForAGivenYear> yearsAndMonths() {
        List<MonthsForAGivenYear> result = new ArrayList<>();
        int[] years = extractYears();
        for (int i = 0; i < years.length; i++) {
            String[] months = getMonthsForAGivenYear(years[i]);
            MonthsForAGivenYear item = new MonthsForAGivenYear(years[i], months);
            result.add(item);
        }
        return result;
    }

    @Override
    public List<City> getAllCitiesForADate(int year, String monthString) {
        User user = sessionObject.getUser();
        Month monthObject = monthDAO.getMonthByNameEng(monthString);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery
                ("FROM me.tomaszterlecki.travel.model.database.Picture WHERE year=:year AND month=:month AND user=:user");
        query.setParameter("year", year);
        query.setParameter("month", monthObject);
        query.setParameter("user", user);
        List<Picture> resultPictures = query.getResultList();
        session.close();

        Set<City> resultSet = new HashSet<>();
        for (Picture picture : resultPictures) {
            resultSet.add(picture.getCity());
        }
        List<City> result = new ArrayList<>();
        for (City city : resultSet) {
            result.add(city);
        }
        return result;
    }

    @Override
    public List<Picture> getPicturesByUserDateAndCity(int year, String monthString, City city) {
        User user = sessionObject.getUser();
        Month monthObject = monthDAO.getMonthByNameEng(monthString);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM me.tomaszterlecki.travel.model.database.Picture WHERE " +
                "year=:year AND month=:month AND city=:city AND user=:user");
        query.setParameter("year", year);
        query.setParameter("month", monthObject);
        query.setParameter("city", city);
        query.setParameter("user", user);
        List<Picture> resultPictures = query.getResultList();
        session.close();
        return resultPictures;
    }

    @Override
    public List<Picture> getPicturesByCity(City city) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM me.tomaszterlecki.travel.model.database.Picture WHERE city=:city");
        query.setParameter("city", city);
        List<Picture> picturesList = query.getResultList();
        session.close();
        return picturesList;
    }

    @Override
    public List<MonthsForAGivenYear> getDatesForACity(City city) {

        List<Picture> pictureList = getPicturesByCity(city);
        int[] years = extractYearsForACity(city);
        List<MonthsForAGivenYear> result = new ArrayList<>();
        for (int i = 0; i < years.length; i++) {
            String[] months = getMonthsForAGivenYearAndCity(years[i], city);
            MonthsForAGivenYear element = new MonthsForAGivenYear(years[i], months);
            result.add(element);
        }
        return result;
    }
    public boolean checkCity(City city){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM me.tomaszterlecki.travel.model.database.Picture WHERE city=:city");
        query.setParameter("city", city);
        List<Picture> pictures = query.getResultList();
        if(pictures.size()>0){return false; }
        return true;
    }


    //    ------- metody prywatne------------
    private int[] extractYears() {
        User user = sessionObject.getUser();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM me.tomaszterlecki.travel.model.database.Picture WHERE user=:user");
        query.setParameter("user", user);
        List<Picture> picturesForGivenUser = query.getResultList();
        int[] result = getYears(picturesForGivenUser);
        session.close();
        Arrays.sort(result);
        return result;
    }

    private int[] extractYearsForACity(City city) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM me.tomaszterlecki.travel.model.database.Picture WHERE city=:city");
        query.setParameter("city", city);
        List<Picture> allPicturesForACity = query.getResultList();
        int[] result = getYears(allPicturesForACity);
        session.close();
        Arrays.sort(result);
        return result;
    }

    private String[] getMonthsForAGivenYear(int year) {
        User user = sessionObject.getUser();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery
                ("FROM me.tomaszterlecki.travel.model.database.Picture WHERE year=:year AND user=:user");
        query.setParameter("year", year);
        query.setParameter("user", user);
        List<Picture> pictures = query.getResultList();
        session.close();
        String[] result = getMonthsEng(pictures);
        return result;
    }

    private String[] getMonthsForAGivenYearAndCity(int year, City city) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery
                ("FROM me.tomaszterlecki.travel.model.database.Picture WHERE year=:year AND city=:city");
        query.setParameter("year", year);
        query.setParameter("city", city);
        List<Picture> pictures = query.getResultList();
        session.close();
        String[] result = getMonthsEng(pictures);
        return result;
    }

    private String[] getMonthsEng(List<Picture> pictures) {
        Set<Integer> monthsIdsSet = new HashSet<>();
        for (Picture picture : pictures) {
            monthsIdsSet.add(picture.getMonth().getId());
        }
        int list = monthsIdsSet.size();
        String[] result = new String[list];
        int[] resultIds = new int[list];
        int i = 0;
        for (int monthId : monthsIdsSet) {
            resultIds[i] = monthId;
            i++;
        }
        Arrays.sort(resultIds);
        for (int j = 0; j < list; j++) {
            result[j]=monthDAO.getMonthById(resultIds[j]).getNameEng();
        }
        return result;

    }

    private int[] getYears(List<Picture> pictures) {
        Set<Integer> yearSet = new HashSet<>();
        for (Picture picture : pictures) {
            yearSet.add(picture.getYear());
        }

        int list = yearSet.size();
        int[] result = new int[list];
        int i = 0;
        for (int year : yearSet) {
            result[i] = year;
            i++;
        }
        return result;
    }

    private int[] getYearsByCity(City city) {
        Session session = sessionFactory.openSession();
        Query<Picture> query = session.createQuery
                ("FROM me.tomaszterlecki.travel.model.database.Picture WHERE city =:city");
        query.setParameter("city", city);
        List<Picture> pictures = query.getResultList();
        session.close();
        int[] result = getYears(pictures);
        return result;
    }

}
