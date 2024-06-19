package com.i2i.sms.controller;

import java.util.List;
import java.util.Scanner;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Cabin;
import com.i2i.sms.service.CabinService;

/**
 * <p>
 * CabinController class handles the interaction between the user and the application.
 * It provides various functionalities such as Viewing all the records that were inserted, 
 * Search for Cabin details, and removing Cabin data from the database.
 * </p>
 */
public class CabinController {

  private static Scanner scanner = new Scanner(System.in);
  private CabinService cabinService = new CabinService();

  /**
   * <p>
   * This method is used to fetch all the Cabin details that where stored.
   * </p>
   */
  public void fetchAllCabins() {
    try {
      List<Cabin> cabins = cabinService.fetchAllCabins();
      if (! cabins.isEmpty()) {
        for (Cabin i : cabins) {
          System.out.println("\n-------------");
          System.out.println(i);
          System.out.println("-------------");
        }
      } else {
        System.out.println("No Cabin Data found while fetching...");
      }
    } catch (StudentManagementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * <p>
   * Search for particular Cabin detail by getting cabin-Id from user.
   * </p>
   */
  public void searchCabinById() { 
    try {
      System.out.println("Enter Cabin-Id to search the Details : ");
      int searchId = scanner.nextInt();
      Cabin cabin = cabinService.searchCabinById(searchId);
      if (null != cabin) {
        System.out.println("\n-------------");
        System.out.println(cabin);
        System.out.println(cabin.getTeacher());
        System.out.println("-------------");
      } else {
        System.out.println("No Cabin Data found on ID - " + searchId);
      }
    } catch (StudentManagementException e) {
      System.out.println(e.getMessage());
    }
  }
}