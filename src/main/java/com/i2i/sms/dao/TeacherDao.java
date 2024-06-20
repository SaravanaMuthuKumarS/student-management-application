package com.i2i.sms.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.models.Teacher;

/**
 * <p>
 * TeacherDao class uses hql queries to create, fetch, search and remove teacher details.
 * It provides various functionalities such as accepting and adding teacher details, 
 * Viewing all the records that were inserted, 
 * Searching for teacher details,  and removing teacher data from the database.
 * </p>
 */ 
public class TeacherDao {

  /**
   * <p>
   * This method is used insert the teacher details into the database.
   * </p>
   * @param teacher
   *       Teacher object.
   * @throws StudentManagementException
   *       When error occurs in insertion process. 
   */ 
  public Teacher saveTeacher(Teacher teacher) throws StudentManagementException {
    Transaction transaction = null;
    try (Session session = HibernateManagement.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.save(teacher);
      transaction.commit();
      return teacher;
    } catch (Exception e) {
      HibernateManagement.rollbackTransaction(transaction);
      throw new StudentManagementException("Error Occured While inserting teacher record : " 
      + teacher.getName());
    }
  }

  /**
   * <p>
   * This method is used search the teacher details by it's id from the database.
   * </p>
   * @param id
   *       Id of the teacher in integer type.
   * @return teacher - Teacher object.
   * @throws StudentManagementException
   *       When error occurs in searching process. 
   */ 
  public Teacher searchTeacherById(int id) throws StudentManagementException {
    try (Session session = HibernateManagement.getSessionFactory().openSession()) {  
      Teacher teacher = session.get(Teacher.class, id); 
      Hibernate.initialize(teacher.getCabin());
      Hibernate.initialize(teacher.getGroups());
      return teacher;
    } catch (Exception e) {
      throw new StudentManagementException("Error Occured While Searching teacher record with Id "
      + id);
    }
  } 

  /**
   * <p>
   * This method is used remove the teacher details by Id from the database.
   * </p>
   * @param id
   *       Id of the teacher to be removed in integer type.
   * @throws StudentManagementException
   *       When error occurs in deletion process. 
   */ 
  public boolean removeTeacherById(int id) throws StudentManagementException {
    Transaction transaction = null;
    try (Session session = HibernateManagement.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Teacher Teacher = session.get(Teacher.class, id);
      if (Teacher!= null) {
        session.delete(Teacher);
        transaction.commit();
        return true;
      }          
    } catch (Exception e) {
      HibernateManagement.rollbackTransaction(transaction);
      throw new StudentManagementException("Error Occured While deleting teacher record with Id : "
      + id);
    }
    return false;
  }

  /**
   * <p>
   * This method is used fetch the teacher details from the database.
   * </p>
   * @return teachers - List of teacher objects.
   * @throws StudentManagementException
   *       When error occurs in fetching process. 
   */ 
  public List<Teacher> fetchAllTeachers() throws StudentManagementException {
    try (Session session = HibernateManagement.getSessionFactory().openSession()) {
      List<Teacher> Teachers = session.createQuery("From Teacher", Teacher.class).list();
      return Teachers;
    } catch (Exception e) {
      throw new StudentManagementException("Error Occured While fetching teacher records ");
    }
  }
}
