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
        String dbUrl = dotenv.get("DB_URL");
        String dbUser = dotenv.get("DB_USER");
        String dbPassword = dotenv.get("DB_PASSWORD");

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setProperty("hibernate.connection.url", dbUrl);
        configuration.setProperty("hibernate.connection.username", dbUser);
        configuration.setProperty("hibernate.connection.password", dbPassword);

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