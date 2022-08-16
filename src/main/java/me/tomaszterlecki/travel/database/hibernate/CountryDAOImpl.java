package me.tomaszterlecki.travel.database.hibernate;

import me.tomaszterlecki.travel.database.ICityDAO;
import me.tomaszterlecki.travel.database.ICountryDAO;
import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CountryDAOImpl implements ICountryDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    ICityDAO cityDAO;

    public List<Country> getAll () {
        Session session = this.sessionFactory.openSession();
        Query<Country> query = session.createQuery("FROM me.tomaszterlecki.travel.model.Country");
        List<Country> result = query.getResultList();
        session.close();
        return result;
    }


    @Override
    public Country getCountryByNameEng(String nameEng) {
        Session session = this.sessionFactory.openSession();
        Query<Country> query = session.createQuery("FROM me.tomaszterlecki.travel.model.Country WHERE nameEng = :nameEng");
        query.setParameter("nameEng", nameEng);
        Country result = query.getSingleResult();
        session.close();
        return result;
    }
    @Override
    public List<CitiesForAGivenCountry> getAllCitiesInCountries(){
        List<Country> countriesSourted = getAllNamesSorted();
        List<CitiesForAGivenCountry> result = new ArrayList<>();
        for (Country country : countriesSourted) {
            List<City> cities = cityDAO.getAllCitiesByCountry(country);
            CitiesForAGivenCountry element = new CitiesForAGivenCountry(country, cities);
            result.add(element);
        }
        return result;
    }
    public List<Country> getAllNamesSorted () {
        Session session = this.sessionFactory.openSession();
        Query<Country> query = session.createQuery("FROM me.tomaszterlecki.travel.model.Country");
        List<Country> countries = query.getResultList();
        session.close();

        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getNameEng().compareTo(o2.getNameEng());
            }
        });
        return countries;
    }
}
