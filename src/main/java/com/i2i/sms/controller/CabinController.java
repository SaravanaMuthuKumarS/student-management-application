package com.i2i.sms.controller;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
@Controller
public class CabinController {

    private Scanner scanner = new Scanner(System.in);
    private final Logger logger = LoggerFactory.getLogger(CabinController.class);
    @Autowired
    private CabinService cabinService;

    /**
     * <p>
     * This method is used to fetch all the Cabin details that where stored.
     * </p>
     */
    public void fetchAllCabins() {
        try {
            List<Cabin> cabins = cabinService.fetchAllCabins();
            if (!cabins.isEmpty()) {
                for (Cabin i : cabins) {
                    System.out.println("\n-------------");
                    System.out.println(i);
                    System.out.println("-------------");
                }
            } else {
                System.out.println("No Cabin Data found while fetching...");
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), "\n", e);
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
            logger.error(e.getMessage(), "\n", e);
            System.out.println(e.getMessage());
        }
    }
}