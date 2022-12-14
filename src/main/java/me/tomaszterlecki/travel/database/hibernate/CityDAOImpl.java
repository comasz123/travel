package me.tomaszterlecki.travel.database.hibernate;

import me.tomaszterlecki.travel.database.ICityDAO;
import me.tomaszterlecki.travel.model.*;
import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.model.database.Country;
import me.tomaszterlecki.travel.model.database.Picture;
import me.tomaszterlecki.travel.model.database.User;
import me.tomaszterlecki.travel.session.SessionObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CityDAOImpl implements ICityDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    SessionObject sessionObject;
//    @Autowired
//    ICountryDAO countryDAO;

    @Override
    public List<City> getAllCitiesByCountry(Country country) {

        Session session = sessionFactory.openSession();
        Query<City> query = session.createQuery
                ("FROM me.tomaszterlecki.travel.model.database.City WHERE country = :country");
        query.setParameter("country", country);
        List<City> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void setCountriesAndCitiesForUser() {
        User user = sessionObject.getUser();
        Session session = sessionFactory.openSession();
        Query<Picture> query = session.createQuery
                ("FROM me.tomaszterlecki.travel.model.database.Picture WHERE user=:user");
        query.setParameter("user", user);
        List<Picture> pictures = query.getResultList();
        session.close();

        Set<City> citiesTravelledSet = new HashSet<>();
        Set<Country> countriesTravelled = new HashSet<>();

        for (Picture picture : pictures) {
            citiesTravelledSet.add(picture.getCity());
            countriesTravelled.add(picture.getCity().getCountry());
        }

        List<CitiesForAGivenCountry> countriesAndCitiesTravelled = new ArrayList<>();
        for (Country country : countriesTravelled) {
            List<City> citiesInACountry = new ArrayList<>();
            for (City city : citiesTravelledSet) {
                if (country.getId() == city.getCountry().getId()) {
                    citiesInACountry.add(city);
                }
            }
            countriesAndCitiesTravelled.add(new CitiesForAGivenCountry(country, citiesInACountry));
        }
        sessionObject.setCitiesForAGivenCountries(countriesAndCitiesTravelled);
    }

    @Override
    public City getCityById(int cityId) {
        Session session = sessionFactory.openSession();
        Query<City> query = session.createQuery("FROM me.tomaszterlecki.travel.model.database.City WHERE id=:cityId");
        query.setParameter("cityId", cityId);
        City result = query.getSingleResult();
        session.close();
        return result;
    }
    @Override
    public List<City> getAllCities(){
        Session session = sessionFactory.openSession();
        Query<City> query = session.createQuery("FROM me.tomaszterlecki.travel.model.database.City");
        List<City> result = query.getResultList();
        session.close();
        return result;
    }



}
