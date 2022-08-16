package me.tomaszterlecki.travel.database.hibernate;

import me.tomaszterlecki.travel.database.IUserDAO;
import me.tomaszterlecki.travel.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {
    @Autowired
    SessionFactory sessionFactory;

    public User getUserById(int id) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM me.tomaszterlecki.travel.model.User WHERE id=:id");
        query.setParameter("id", id);
        User result = query.getSingleResult();
        session.close();
        return result;
    }

    public Optional<User> getUserByLogin(String login) {
        Session session = sessionFactory.openSession();

        try {
            Query<User> query =
                    session.createQuery("FROM me.tomaszterlecki.travel.model.User WHERE login=:login");
            query.setParameter("login", login);
            User result = query.getSingleResult();
            session.close();
            return Optional.of(result);
        } catch (NoResultException e){
            session.close();
            return Optional.empty();
        }
    }
}
