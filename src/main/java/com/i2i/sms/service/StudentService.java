package com.i2i.sms.service;

import java.util.List;
import java.time.LocalDate;

import com.i2i.sms.dao.StudentDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Group;
import com.i2i.sms.models.Student;

/**
 * <p>
 * This class provides services related to student management. 
 * Operations like insert, delete, search and fetch 
 * The student details from the database are performed.
 * </p>
 */
public class StudentService {
 
  private GroupService groupService = new GroupService();
  private StudentDao studentDao = new StudentDao();

  /**
   * <p>
   * This method is used insert the student details into the database.
   * </p>
   * @param name
   *       name of the student in String format.
   * @param dob
   *       dob of the student in Date format.
   * @param group
   *       group object of the student to which he belongs.
   * @return Student object from dao.
   * @throws StudentManagementException
   *       When error occurs in insertion process. 
   */ 
  public Student addStudent(String name, LocalDate dob, Group group) 
  throws StudentManagementException { 
    Student student = new Student(name, dob);
    student.setGroup(group);
    return studentDao.createStudent(student);
  }

  /**
   * <p>
   * This method is used to fetch all the students details from the database.
   * </p>
   * @return Array of Student objects from dao.
   * @throws StudentManagementException
   *      When error occurs in fetching process. 
   */ 
  public List<Student> fetchAllStudents() throws StudentManagementException {
    return studentDao.fetchAllStudents();
  }

  /**
   * <p>
   * This method is used to search the students detail by it's id from the database.
   * </p>
   * @param id
   *       Id of the student to be searched in integer type.
   * @return Student object from dao.
   * @throws StudentManagementException
   *       When error occurs in Search process. 
   */ 
  public Student searchStudentById(int id) throws StudentManagementException {
    return studentDao.searchStudentById(id);
  }

  /**
   * <p>
   * This method is used to remove the students detail by Id from the database.
   * </p>
   * @param id
   *       Id of the student to be removed in integer type.
   * @retrun true when removed false otherwise.
   * @throws StudentManagementException
   *       When error occurs in deletion process. 
   */ 
  public boolean removeStudentById(int id) throws StudentManagementException {
    return studentDao.removeStudentById(id);
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