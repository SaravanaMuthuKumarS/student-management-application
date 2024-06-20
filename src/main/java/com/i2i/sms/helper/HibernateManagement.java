package com.i2i.sms.helper ;

import org.hibernate.cfg.Configuration ;
import org.hibernate.SessionFactory ;
import org.hibernate.Transaction ;

/**
 * <p>
 * This HibernateManagement class is responsible for creating and managing the Hibernate SessionFactory and rollback operation.
 * </p>
 */
public class HibernateManagement {
  private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

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