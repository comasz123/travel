package me.tomaszterlecki.travel.database.hibernate;

import me.tomaszterlecki.travel.database.IMonthDAO;
import me.tomaszterlecki.travel.model.Month;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MonthDAOImpl implements IMonthDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Month getMonthByNameEng(String name) {
        Session session = sessionFactory.openSession();
        Query<Month> query = session.createQuery("FROM me.tomaszterlecki.travel.model.Month WHERE nameEng=:name");
        query.setParameter("name", name);
        Month result = query.getSingleResult();
        return result;
    }
}
