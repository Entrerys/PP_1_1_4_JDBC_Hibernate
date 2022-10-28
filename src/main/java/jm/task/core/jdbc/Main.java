package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class Main {

    public static void main(String[] args) {

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
        Util.getSessionFactory().close();

    }
}
