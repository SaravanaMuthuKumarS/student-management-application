package com.i2i.sms.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Group;
import com.i2i.sms.models.Teacher;
import com.i2i.sms.service.GroupService;
import com.i2i.sms.service.TeacherService;
import com.i2i.sms.utils.ValidateDataUtils;

/**
 * <p>
 * TeacherController class handles the interaction between the user and the application.
 * It provides various functionalities such as accepting and adding Teacher details
 * Viewing all the records that were inserted, 
 * Search for Teacher details, and removing Teacher data from the database.
 * </p>
 */
public class TeacherController {

  private static Scanner scanner = new Scanner(System.in);
  private GroupService groupService = new GroupService();
  private TeacherService teacherService = new TeacherService();

  /**
   * <p>
   * This method is used to get user inputs and passes the details to the addTeacher 
   * Method of service class. Inputs from the teacher such as there name, subject,
   * Department and there handling standards and sections are entered by the user in this method.
   * </p>
   */
  public void createTeacher() {
    try {
      String teacherName, subject, department, section;
      int standard;
      while (true) {
        System.out.println("Enter your Name :");
        teacherName = scanner.next();
        if (ValidateDataUtils.stringValidator(teacherName)) {
          break;
        }
        System.out.println("Invalid String input for Teacher Name..");
      }

      while (true) {
        System.out.println("Enter the Subject Name you are Handling : ");
        subject = scanner.next();
        if (ValidateDataUtils.stringValidator(subject)) {
          break;
        }
        System.out.println("Invalid String input for Subject Name..");
      }

      while (true) {
        System.out.println("Enter your Department :");
        department = scanner.next();
        if (ValidateDataUtils.stringValidator(department)) {
          break;
        }
        System.out.println("Invalid String input for Department Name..");
      }

      Set<Group> groups = new HashSet<>();
      System.out.println("Kindly Enter the Group's Data you are Handling...");
      while (true) {
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

        Group group = teacherService.getOrCreateGroup(standard, section);
        groups.add(group);
        System.out.println("\n1--> Next Group Details \n2--> Exit Adding process...");
        int option = scanner.nextInt();
        if (option == 2) {
          break;
        }   
      }
      Teacher teacher = teacherService.addTeacher(teacherName, subject, department, groups);
      System.out.println("\nCreating...");
      System.out.println("\nTeacher Data Added to Database...\n");
      System.out.println("\n" + teacher);
      System.out.println(teacher.getCabin());
      System.out.println(groups);
    } catch (StudentManagementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * <p>
   * This method is used to fetch all the Teacher details that where stored.
   * </p>
   */
  public void fetchAllTeachers() {
    try {
      List<Teacher> teachers = teacherService.fetchAllTeachers();
      if (! teachers.isEmpty()) {
        for (Teacher i : teachers) {
          System.out.println("\n-------------");
          System.out.println(i);
          System.out.println("-------------");
        }
      } else {
        System.out.println("No Teacher Data found while fetching...");
      }
    } catch (StudentManagementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * <p>
   * Search for particular Teacher detail by getting teacher-Id from user.
   * </p>
   */
  public void searchTeacherById() {
    try {
      System.out.println("Enter Teacher-Id to search the Details : ");
      int searchId = scanner.nextInt();
      Teacher teacher = teacherService.searchTeacherById(searchId);
      if (null != teacher) {
        System.out.println("\n-------------");
        System.out.println(teacher);
        System.out.println(teacher.getCabin());
        System.out.println(teacher.getGroups());
        System.out.println("-------------");
      } else {
        System.out.println("No Teacher Data found on ID - " + searchId);
      }
    } catch (StudentManagementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * <p>
   * Remove a particular Teacher detail by getting teacher-Id from the user.
   * </p>
   */
  public void removeTeacherById() {
    try {
      System.out.println("Enter Teacher-Id to remove the Details : ");
      int removeId = scanner.nextInt();
      boolean deleteFlag = teacherService.removeTeacherById(removeId);
      if (deleteFlag) {
        System.out.println("-------------");
        System.out.println("Teacher Data Removed Successfully");
        System.out.println("-------------");
      } else {
        System.out.println("No Teacher Data found on ID - " + removeId);
      }
    } catch (StudentManagementException e) {
      System.out.println(e.getMessage());
    }
  }
}