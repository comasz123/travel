package me.tomaszterlecki.travel.database.hibernate;

import me.tomaszterlecki.travel.database.ICityDAO;
import me.tomaszterlecki.travel.database.ICountryDAO;
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

    public List<Country> getAllCountries() {
        Session session = this.sessionFactory.openSession();
        Query<Country> query = session.createQuery("FROM me.tomaszterlecki.travel.model.Country");
        List<Country> countries = query.getResultList();
        session.close();
        return getCountryNamesSortedInEng(countries);
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
    public Country getCountryByID(int id){
        Session session = this.sessionFactory.openSession();
        Query<Country> query = session.createQuery("FROM me.tomaszterlecki.travel.model.Country WHERE id = :id");
        query.setParameter("id", id);
        Country result = query.getSingleResult();
        session.close();
        return result;
    }

    //    @Override
//    public List<CitiesForAGivenCountry> getAllCitiesInAllCountries(){
//
//        Session session = this.sessionFactory.openSession();
//        Query<Country> query = session.createQuery("FROM me.tomaszterlecki.travel.model.Country");
//        List<Country> countries = query.getResultList();
//        session.close();
//        List<Country> countryNamesSorted = getCountryNamesSortedInEng(countries);
//
//        List<CitiesForAGivenCountry> result = new ArrayList<>();
//        for (Country country : countryNamesSorted) {
//            List<City> cities = cityDAO.getAllCitiesByCountry(country);
//            result.add(new CitiesForAGivenCountry(country, cities));
//        }
//        return result;
//    }
    public List<Country> getCountryNamesSortedInEng (List<Country> countries) {
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getNameEng().compareTo(o2.getNameEng());
            }
        });
        return countries;
    }
}
