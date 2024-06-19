package com.i2i.sms.controller;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.sms.helper.HibernateConnection;
import com.i2i.sms.model.Student;

public class CommonController {
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Enter student name:");
    String name = scanner.next();
    System.out.println("Enter Your Standard : ");
    int standard = scanner.nextInt();
    System.out.println("Enter Your Section : ");
    String section = scanner.next();
    Student student = new Student(name, standard, section);
    student = insertStudent(student);
    System.out.println("Student saved successfully");
    System.out.println(student);
  }

  public static Student insertStudent(Student student) {
    Transaction transaction = null;
    try (Session session = HibernateConnection.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.save(student);
      transaction.commit();
    } catch (Exception e) {
      HibernateConnection.rollbackTransaction(transaction);
      System.out.println("Error Occured while adding student");
    }
    return student;
  }
}

