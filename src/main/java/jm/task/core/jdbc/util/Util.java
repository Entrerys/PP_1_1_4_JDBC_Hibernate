package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynewdb", "root",
                    "159951");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static  SessionFactory sessionFactory = getSessionFactory();
    public static SessionFactory getSessionFactory() {
        if( sessionFactory == null){
            try{
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL,"jdbc:mysql://localhost:3306/mynewdb");
                settings.put(Environment.USER,"root");
                settings.put(Environment.PASS,"159951");
                settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL,"true");
                settings.put(Environment.HBM2DDL_AUTO,"create");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
