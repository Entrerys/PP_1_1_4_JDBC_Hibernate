package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.util.UtilHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.PersistenceException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("create table users" +
                    "(id Int Primary Key NOT NULL AUTO_INCREMENT, name VARCHAR(30) NOT NULL," +
                    " lastName VARCHAR(30) NOT NULL, age Int NOT NULL)").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        }catch (PersistenceException e){
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("drop table users").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        }catch (PersistenceException e){
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            session.save(new User(name, lastName, age));
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();

        }

    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("DELETE from users").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }
}
