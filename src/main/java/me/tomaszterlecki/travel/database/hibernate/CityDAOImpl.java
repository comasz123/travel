package me.tomaszterlecki.travel.database.hibernate;

import me.tomaszterlecki.travel.database.ICityDAO;
import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDAOImpl implements ICityDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<City> getAllCitiesByCountry(Country country) {
        Session session = sessionFactory.openSession();
        Query<City> query = session.createQuery("FROM me.tomaszterlecki.travel.model.City WHERE country = :country");
        query.setParameter("country", country);
        List<City> result = query.getResultList();
        session.close();
        return result;
    }
    @Override
    public City getCityById(int cityId){
        Session session = sessionFactory.openSession();
        Query<City> query = session.createQuery("FROM me.tomaszterlecki.travel.model.City WHERE id=:cityId");
        query.setParameter("cityId", cityId);
        City result = query.getSingleResult();
        session.close();
        return result;
    }
}
