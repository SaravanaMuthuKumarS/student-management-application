package com.i2i.sms.controller;

import java.util.List;
import java.util.Scanner;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Group;
import com.i2i.sms.service.GroupService;
import com.i2i.sms.utils.ValidateDataUtils;

/**
 * <p>
 * GroupController class handles the interaction between the user and the application.
 * It provides various functionalities such as accepting and adding Group details
 * Viewing all the records that were inserted, 
 * Search for group details, and removing group data from the database.
 * </p>
 */
public class GroupController {

  private static Scanner scanner = new Scanner(System.in);
  private GroupService groupService = new GroupService();

  /**
   * <p>
   * This method is used to get user inputs and passes the details to the getOrCreateGroup 
   * Method of service class. Inputs from the User such as standard and section are
   * Used to create the group.
   * </p>
   */
  public void createGroup() {
    try {
      int standard;
      String section;
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

      System.out.println("\nCreating Group...\n");
      Group group = groupService.getOrCreateGroup(standard, section);
      System.out.println("\n" + group);
    } catch (StudentManagementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * <p>
   * This method is used to fetch all the Group details that where stored.
   * </p>
   */
  public void fetchAllGroups() {
    try {
      List<Group> groups = groupService.fetchAllGroups();
      if (! groups.isEmpty()) {
        for (Group i : groups) {
          System.out.println("\n-------------");
          System.out.println(i);
          System.out.println("-------------");
        }
      } else {
        System.out.println("No Group Data found while fetching...");
      }
    } catch (StudentManagementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * <p>
   * Search for particular Group detail by getting group-Id from user.
   * </p>
   */
  public void searchGroupById() {
    try {
      System.out.println("Enter Group-Id to search the Details : ");
      int searchId = scanner.nextInt();
      Group group = groupService.searchGroupById(searchId);
      if (null != group) {
        System.out.println("\n-------------");
        System.out.println(group);
        System.out.println(group.getStudents());
        System.out.println(group.getTeachers());
        System.out.println("-------------");
      } else {
        System.out.println("No Group Data found on ID - " + searchId);
      }
    } catch (StudentManagementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * <p>
   * Remove a particular Group detail by getting group-Id from the user.
   * </p>
   */
  public void removeGroupById() {
    try {
      System.out.println("Enter Group-Id to remove the Details : ");
      int removeId = scanner.nextInt();
      boolean deleteFlag = groupService.removeGroupById(removeId);
      if (deleteFlag) {
        System.out.println("-------------");
        System.out.println("Group Data Removed Successfully");
        System.out.println("-------------");
      } else {
        System.out.println("No Group Data found on ID - " + removeId);
      }
    } catch (StudentManagementException e) {
      System.out.println(e.getMessage());
    }
  }
}