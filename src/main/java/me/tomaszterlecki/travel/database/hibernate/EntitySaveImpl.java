package me.tomaszterlecki.travel.database.hibernate;

import me.tomaszterlecki.travel.database.IEntitySaver;
import me.tomaszterlecki.travel.model.Writeable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntitySaveImpl implements IEntitySaver {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistEntity(Writeable entity) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
        } catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public void updateEntity(Writeable entity) {

    }

    @Override
    public void deleteEntity(Writeable entity) {

    }

}
