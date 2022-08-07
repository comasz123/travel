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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public String[] getAllNamesSorted () {
        Session session = this.sessionFactory.openSession();
        Query<Country> query = session.createQuery("FROM me.tomaszterlecki.travel.model.Country");
        List<Country> countries = query.getResultList();
        session.close();
        String[] result = new String[countries.size()];
        int i = 0;
        for (Country country:countries) {
            result[i]=country.getNameEng();
            i++;
        }
        Arrays.sort(result);
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
        String[] countryNames = getAllNamesSorted();
        List<CitiesForAGivenCountry> result = new ArrayList<>();
        for (String countryName : countryNames) {
            Country country = getCountryByNameEng(countryName);
            List<City> cities = cityDAO.getAllCitiesByCountry(country);
            CitiesForAGivenCountry element = new CitiesForAGivenCountry(countryName, cities);
            result.add(element);
        }
        return result;
    }
}
