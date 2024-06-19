package com.i2i.sms.helper;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * <p>
 * This HibernateManagement class is responsible for creating and managing the Hibernate SessionFactory and rollback operation.
 * </p>
 */
public class HibernateConnection {
  private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

  /**
   * <p>
   * SessionFactory method is responsible for returning the Hibernate SessionFactory.
   * </p>
   * @return sessionFactory
   */
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  /**
   * <p>
   * This method is used rollback the operations done to previous commit.
   * </p>
   * @param transaction
   *       transaction object.
   */
  public static void rollbackTransaction(Transaction transaction) {
    try {
      if (null != transaction) {
        transaction.rollback();
      }
    } catch(Exception e) {
      System.out.println("Error occurred in rollback process...");
    }
  }
}