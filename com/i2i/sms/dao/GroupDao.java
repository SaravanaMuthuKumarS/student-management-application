package com.i2i.sms.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.models.Group;
import com.i2i.sms.models.Student;
import com.i2i.sms.models.Teacher;

/**
 * <p>
 * GroupDao class uses hql to create, search, fetch and remove group details.
 * It provides various functionalities such as get or add group details, viewing all the records 
 * That were inserted, searching for groups details,  and removing group data from the database.
 * </p>
 */ 
public class GroupDao {

  /**
   * <p>
   * This method is used fetch the group from the database.
   * </p>
   * @param group
   *       Group object.  
   * @return group - group object.
   * @throws StudentManagementException
   *       When error occurs in get or create process. 
   */ 
  public Group getOrCreateGroup(Group group) throws StudentManagementException {
    Transaction transaction = null;
    try (Session session = HibernateManagement.getSessionFactory().openSession()) { 
      transaction = session.beginTransaction();    
      Query<Group> query = session.createQuery("FROM Group WHERE standard = :standard AND section"
      + " = :section", Group.class);
      query.setParameter("standard", group.getStandard());
      query.setParameter("section", group.getSection());            
      Group groupData = query.uniqueResult(); 
      if (null == groupData) {
        session.save(group);
        transaction.commit();
        return group;
      } else {  
        return groupData;
      }
    } catch (Exception e) {
      HibernateManagement.rollbackTransaction(transaction);
      throw new StudentManagementException("Error Occured While inserting Group Record with Standard"
      + " - Section : " + group.getStandard() + " - " + group.getSection());
    }
  }

  /**
   * <p>
   * This method is used search the group details by it's id from the database.
   * </p>
   * @param id
   *       Id of the student to be searched in integer type.
   * @return group - Group object.
   * @throws StudentManagementException
   *       When error occurs in searching process. 
   */ 
  public Group searchGroupById(int id) throws StudentManagementException {
    try (Session session = HibernateManagement.getSessionFactory().openSession()) {
      Group group = session.get(Group.class, id);
      Hibernate.initialize(group.getStudents());
      Hibernate.initialize(group.getTeachers());   
      return group;
    } catch (Exception e) {
      throw new StudentManagementException("Error Occured While searching Group Record with ID "
      + id);
    }
  } 

  /**
   * <p>
   * This method is used fetch the group details from the database.
   * </p>
   * @return groups - List of group objects.
   * @throws StudentManagementException
   *       When error occurs in fetching process. 
   */ 
  public List<Group> fetchAllGroups() throws StudentManagementException {
    try (Session session = HibernateManagement.getSessionFactory().openSession()) {
      List<Group> groups = session.createQuery("From Group", Group.class).list();
      return groups;
    } catch (Exception e) {
      throw new StudentManagementException("Error Occured While Fetching Groups Record ");
    }
  }

  /**
   * <p>
   * This method is used remove the group details by Id from the database.
   * </p>
   * @param id
   *       Id of the group to be removed in integer type.
   * @retrun true when removed false otherwise.
   * @throws StudentManagementException
   *       When error occurs in deletion process. 
   */ 
  public boolean removeGroupById(int id) throws StudentManagementException {
    Transaction transaction = null;
    try (Session session = HibernateManagement.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Group group = session.get(Group.class, id);
      if (null != group) {
        session.delete(group);
        transaction.commit();
        return true;
      }          
    } catch (Exception e) {
      HibernateManagement.rollbackTransaction(transaction);
      throw new StudentManagementException("Error Occured While deleting Group Record with Id : "
      + id);
    }
    return false;
  }
}
