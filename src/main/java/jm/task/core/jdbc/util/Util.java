package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String BD_DRIVERS = "com.mysql.cj.jdbc.Driver";
    private static final String BD_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String BD_USER = "root";
    private static final String BD_PASSWORD = "TimBurton666";
    private static Connection JBDCconnection;


    public static Connection getJBDCConnection() {

        try {
            if( JBDCconnection == null || JBDCconnection.isClosed()) {
                Class.forName(BD_DRIVERS);
                JBDCconnection = DriverManager.getConnection(BD_URL,BD_USER,BD_PASSWORD);
                JBDCconnection.setAutoCommit(false);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return JBDCconnection;

    }

    public static void close() {
        try {
            if (JBDCconnection != null && !JBDCconnection.isClosed()) {
                JBDCconnection.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
