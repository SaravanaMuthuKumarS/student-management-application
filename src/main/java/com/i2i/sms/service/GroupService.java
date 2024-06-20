package com.i2i.sms.service;

import com.i2i.sms.dao.GroupDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Student;
import com.i2i.sms.models.Group;
import com.i2i.sms.models.Teacher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * This class provides services related to group management.
 * Operations like insert, delete, search and fetch 
 * The group details from the database are performed.
 * </p>
 */
public class GroupService {
   
  private GroupDao groupDao = new GroupDao();

  /**
   * <p>
   * This method is used to add or fetch a group data in database.
   * </p>
   * @param standard
   *       standard of the student in integer type. 
   * @param section
   *       section of the student in String type. 
   * @return Group object from dao.
   * @throws StudentManagementException
   *       When error occurs in fetching process. 
   */ 
  public Group getOrCreateGroup(int standard, String section)
  throws StudentManagementException {
    Group group = new Group(standard, section); 
    return groupDao.getOrCreateGroup(group);
  }

  /**
   * <p>
   * This method is used to search the group data by it's id from the database.
   * </p>
   * @param id
   *       Id of the student to be searched in integer type.
   * @return Group object from dao.
   * @throws StudentManagementException
   *       When error occurs in searching process. 
   */ 
  public Group searchGroupById(int id) throws StudentManagementException {
    return groupDao.searchGroupById(id);
  }

  /**
   * <p>
   * This method is used to fetch the group details from the database.
   * </p>
   * @return Array of Group objects from dao.
   * @throws StudentManagementException
   *       When error occurs in fetching process. 
   */ 
  public List<Group> fetchAllGroups() throws StudentManagementException {
    return groupDao.fetchAllGroups();
  }

  /**
   * <p>
   * This method is used to remove the group data by Id from the database.
   * </p>
   * @param id
   *       Id of the group to be removed in integer type.
   * @retrun true when removed false otherwise.
   * @throws StudentManagementException
   *       When error occurs in deletion process. 
   */ 
  public boolean removeGroupById(int id) throws StudentManagementException {
    return groupDao.removeGroupById(id);
  }
}