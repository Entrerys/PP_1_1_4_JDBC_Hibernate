package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.PersistenceException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    private final SessionFactory sessionFactory = Util.getUtil().getSessionFactory();

    private Transaction transaction = null;

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("create table IF NOT EXISTS users" +
                    "(id Int Primary Key NOT NULL AUTO_INCREMENT, name VARCHAR(30) NOT NULL," +
                    " lastName VARCHAR(30) NOT NULL, age Int NOT NULL)").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("Drop TABLE IF Exists users").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        } catch (PersistenceException e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));
            transaction.commit();

        } catch (PersistenceException | IllegalArgumentException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (PersistenceException | IllegalArgumentException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from User", User.class).list();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("TRUNCATE TABLE users").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
