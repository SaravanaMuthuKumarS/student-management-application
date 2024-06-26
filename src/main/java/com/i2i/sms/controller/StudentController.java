package com.i2i.sms.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Group;
import com.i2i.sms.models.Student;
import com.i2i.sms.service.StudentService;
import com.i2i.sms.utils.DateUtils;
import com.i2i.sms.utils.ValidateDataUtils;

/**
 * <p>
 * StudentController class handles the interaction between the user and the application.
 * It provides various functionalities such as accepting and adding Students details
 * Viewing all the records that were inserted, 
 * Search for Student details, and removing Student data from the database.
 * </p>
 */
public class StudentController {

  private static Scanner scanner = new Scanner(System.in);
  private final Logger logger = LoggerFactory.getLogger(StudentController.class);
  private StudentService studentService = new StudentService();

  /**
   * <p>
   * This method is used to get user inputs and passes the details to the addStudent 
   * Method of service class. Inputs from the student such as there name, date of birth,
   * Standard and section are entered by the user in this method.
   * </p>
   */
  public void createStudent() {
    try {
      String name, dob, section;
      int standard;
      while (true) {
        System.out.println("Enter your Name :");
        name = scanner.next();
        if (ValidateDataUtils.stringValidator(name)) {
          break;
        }
        System.out.println("Invalid String input for Student Name..");
      }

      while (true) {
        System.out.println("Enter your Date of Birth as YYYY-MM-DD :");
        dob = scanner.next();
        if ((DateUtils.isValidDate(dob))) {
          break;
        }
        System.out.println("Invalid Date Format...Enter as Accepted : YYYY-MM-DD ");
      }
      LocalDate dateOfBirth = LocalDate.parse(dob);

      while (true) {
        System.out.println("Enter your Standard :");
        standard = scanner.nextInt();
        if (ValidateDataUtils.rangeValidator(standard, 1, 12)) {
          break;
        }
        System.out.println("Invalid Standard Data Entered..Accepts only STD 1 - 12..");
      }

      while (true) {
        System.out.println("Enter your Section ");
        section = scanner.next();
        if (ValidateDataUtils.stringValidator(section)) {
          break;
        }
        System.out.println("Invalid Character input Student Section..");
      }
      section = section.toUpperCase();

      logger.info("Entered Details by the User : \n Name - {}, Date of Birth - {} \n Standard - {} and Section - {} ", name, dob, standard, section);
      Group group = studentService.getOrCreateGroup(standard, section);
      Student student = studentService.addStudent(name, dateOfBirth, group);
      System.out.println("Student added successfully.");
      System.out.println("\n" + student);
      System.out.println(student.getGroup());
    } catch (StudentManagementException e) {
      logger.error(e.getMessage(),e);
      System.out.println(e.getMessage());
    }
  }

  /**
   * <p>
   * This method is used to fetch all the Student details that where stored.
   * </p>
   */
  public void fetchAllStudents() {
    try {
      List<Student> students = studentService.fetchAllStudents();
      if (! students.isEmpty()) {
        for (Student i : students) {
          System.out.println("\n-------------");
          System.out.println(i);
          System.out.println("-------------");
        }
      } else {
        System.out.println("No Student Data found while fetching...");
      }
    } catch (StudentManagementException e) {
      logger.error(e.getMessage(),"\n",e);
      System.out.println(e.getMessage());
    }
  }

  /**
   * <p>
   * Search for particular Student detail by getting student-Id from user.
   * </p>
   */
  public void searchStudentById() {
    try {
      System.out.println("Enter Student-Id to search the Details : ");
      int searchId = scanner.nextInt();
      Student student = studentService.searchStudentById(searchId);
      if (null != student) {
        System.out.println("\n-------------");
        System.out.println(student);
        System.out.println(student.getGroup());
        System.out.println("-------------");
      } else {
        System.out.println("No Student Data found on ID - " + searchId);
      }
    } catch (StudentManagementException e) {
      logger.error(e.getMessage(),"\n",e);
      System.out.println(e.getMessage());
    }
  }

  /**
   * <p>
   * Remove a particular Student detail by getting student-Id from the user.
   * </p>
   */
  public void removeStudentById() {
    try {
      System.out.println("Enter Student-Id to remove the Details : ");
      int removeId = scanner.nextInt();
      boolean deleteFlag = studentService.removeStudentById(removeId);
      if (deleteFlag) {
        System.out.println("-------------");
        System.out.println("Student Data Removed Successfully");
        System.out.println("-------------");
      } else {
        System.out.println("No Student Data found on ID - " + removeId);
      } 
    } catch (StudentManagementException e) {
      logger.error(e.getMessage(),"\n",e);
      System.out.println(e.getMessage());
    }
  }
}