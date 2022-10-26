package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.util.UtilHibernate;
import org.hibernate.Session;

import java.util.List;

public class Main {

    public static void main(String[] args) {

//        Util.getConnection();
//        UserDao userDao = new UserDaoJDBCImpl();
//
//        userDao.createUsersTable();
//
//        userDao.saveUser("Name1", "LastName1", (byte) 20);
//        System.out.println("User с именем – Name1 добавлен в базу данных");
//        userDao.saveUser("Name2", "LastName2", (byte) 25);
//        System.out.println("User с именем – Name2 добавлен в базу данных");
//        userDao.saveUser("Name3", "LastName3", (byte) 31);
//        System.out.println("User с именем – Name3 добавлен в базу данных");
//        userDao.saveUser("Name4", "LastName4", (byte) 38);
//        System.out.println("User с именем – Name4 добавлен в базу данных");
//
//        List<User> users = userDao.getAllUsers();
//        for (User user : users) {
//            System.out.println(user);
//        }
//
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();


        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        Session session = Util.getSessionFactory().openSession();
        session.getTransaction().begin();

        session.save(new User("one","one",(byte)27));
        System.out.println("User с именем – one добавлен в базу данных");
        session.save(new User("two","two",(byte)27));
        System.out.println("User с именем – two добавлен в базу данных");
        session.save(new User("three","three",(byte)27));
        System.out.println("User с именем – three добавлен в базу данных");
        session.save(new User("four","four",(byte)27));
        System.out.println("User с именем – four добавлен в базу данных");

        session.getTransaction().commit();

        List<User> users = userDaoHibernate.getAllUsers();
        for(User user : users){
            System.out.println(user);
        }

        session.close();
        UtilHibernate.getSessionFactory().close();

    }
}
