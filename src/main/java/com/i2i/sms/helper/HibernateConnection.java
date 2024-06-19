package com.i2i.sms.helper;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateConnection {
  private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

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