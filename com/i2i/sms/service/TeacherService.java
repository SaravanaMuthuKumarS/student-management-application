package com.i2i.sms.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.i2i.sms.dao.TeacherDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Cabin;
import com.i2i.sms.models.Group;
import com.i2i.sms.models.Teacher;

/**
 * <p>
 * This class provides services related to teacher management
 * Operations like insert, delete, search and fetch 
 * The teacher details from the database are performed.
 * </p>
 */
public class TeacherService {

  private GroupService groupService = new GroupService();
  private TeacherDao teacherDao = new TeacherDao();

  /**
   * <p>
   * This method is used insert the teacher details into the database.
   * </p>
   * @param teacherName
   *       Name of the teacher in String type.
   * @param subject
   *       Handling Subject in String type.
   * @param department
   *       teacher's department in string type.
   * @param groups
   *       set of group objects.
   * @return Teacher object from dao.
   * @throws StudentManagementException
   *       When error occurs in insertion process. 
   */ 
  public Teacher addTeacher(String teacherName, String subject, String department, Set<Group> groups)
  throws StudentManagementException {
    Teacher teacher = new Teacher(teacherName, subject);
    teacher.setGroups(groups);
    Cabin cabin = new Cabin(department);
    teacher.setCabin(cabin);
    return teacherDao.saveTeacher(teacher);
  }

  /**
   * <p>
   * This method is used fetch the teacher details from the database.
   * </p>
   * @return Array of Teacher objects from dao.
   * @throws StudentManagementException
   *       When error occurs in fetching process. 
   */ 
  public List<Teacher> fetchAllTeachers() throws StudentManagementException {
    return teacherDao.fetchAllTeachers();
  }

  /**
   * <p>
   * This method is used search the teacher details by it's id from the database.
   * </p>
   * @param id
   *       Id of the teacher to be searched in integer type.
   * @return Teacher object from dao.
   * @throws StudentManagementException
   *       When error occurs in searching process. 
   */ 
  public Teacher searchTeacherById(int id) throws StudentManagementException {
    return teacherDao.searchTeacherById(id);
  }

  /**
   * <p>
   * This method is used remove the teacher details by Id from the database.
   * </p>
   * @param id
   *       Id of the teacher to be removed in integer type.
   * @return true if record deleted else false.
   * @throws StudentManagementException
   *       When error occurs in deletion process. 
   */ 
  public boolean removeTeacherById(int id) throws StudentManagementException {
    return teacherDao.removeTeacherById(id);
  }

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
  public Group getOrCreateGroup(int standard, String section) throws StudentManagementException {
    return groupService.getOrCreateGroup(standard, section);
  }
}