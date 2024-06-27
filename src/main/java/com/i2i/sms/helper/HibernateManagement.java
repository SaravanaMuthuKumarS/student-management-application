package com.i2i.sms.helper;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * <p>
 * This HibernateManagement class is responsible for creating and managing the Hibernate SessionFactory and rollback operation.
 * </p>
 */
public class HibernateManagement {
    private static final SessionFactory sessionFactory;

    static {
        Dotenv dotenv = Dotenv.load();
        Configuration configuration = new Configuration().configure();
        configuration.setProperty("hibernate.connection.url", dotenv.get("dbUrl"));
        configuration.setProperty("hibernate.connection.username", dotenv.get("dbUser"));
        configuration.setProperty("hibernate.connection.password", dotenv.get("dbPassword"));
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * <p>
     * This method is used rollback the operations done to previous commit.
     * </p>
     */
    public static void rollbackTransaction(Transaction transaction) {
        try {
            if (null != transaction) {
                transaction.rollback();
            }
        } catch (Exception e) {
            System.out.println("Could not roll back transaction");
        }
    }
}