package com.i2i.sms.controller;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Admin;
import com.i2i.sms.service.AdminService;

/**
 * <p>
 * AdminController class handles the interaction between the Master admin and the admins.
 * It provides various functionalities such as Viewing all the admin records that were inserted,
 * Adding a admin details, and removing admin data from the database.
 * </p>
 */
@Controller
@PropertySource("classpath:application.properties")
public class AdminController {
    @Autowired
    private AdminService adminService;
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private Scanner scanner = new Scanner(System.in);
    @Value("${appAdmin}")
    private String master;
    @Value("${password}")
    private String password;

    /**
     * <p>
     * Validates admin credentials by checking the username and password that user enters.
     * </p>
     * @return true if credentials are valid, otherwise false.
     */
    public boolean isAdminAvailable() {
        try {
            System.out.println("\nEnter UserName :");
            String user = scanner.next();
            System.out.println("\nEnter Password :");
            String key = scanner.next();
            System.out.print("\n");
            return adminService.isAdminAvailable(user, key);
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(),"\n",e);
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * <p>
     * Validates admin credentials by checking the username and password that user enters.
     * </p>
     * @return true if credentials are valid, otherwise false.
     */
    public boolean adminValidate() {
        StringBuilder suspiciousUser = new StringBuilder(new String());
        for (int i = 2;i >= 0;i--) {
            System.out.println("\nEnter UserName :");
            String user = scanner.next();
            System.out.println("\nEnter Password :");
            String key = scanner.next();
            System.out.print("\n");
            if (user.equals(master) && key.equals(password)) {
                return true;
            }
            else {
                suspiciousUser.append(user);
                suspiciousUser.append(" ");
                System.out.println("Invalid Credentials provided, you have " + i + " attempts left\n");
            }
        }
        logger.warn("Suspicious Activity Detected with Usernames : {} ",suspiciousUser);
        return false;
    }

    /**
     * <p>
     * This method is used to get user inputs and passes the details of the Admin credentials
     * to the createAdmin method of service class.
     * Inputs from the Admin such as userName and password.
     * </p>
     */
    public void createAdmin() {
        try {
            System.out.println("\nEnter UserName :");
            String user = scanner.next();
            System.out.println("\nEnter Password :");
            String key = scanner.next();
            System.out.print("\n");
            logger.info("Details entered by the admin : {}",user);
            Admin admin = adminService.createAdmin(user,key);
            System.out.println("Admin added successfully.");
            System.out.println("\n" + admin);
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
    public void removeAdminById() {
        try {
            System.out.println("Enter Admin-Id to remove the Details : ");
            int removeId = scanner.nextInt();
            boolean deleteFlag = adminService.removeAdminById(removeId);
            if (deleteFlag) {
                System.out.println("-------------");
                System.out.println("Admin Data Removed Successfully");
                System.out.println("-------------");
            } else {
                System.out.println("No Admin Data found on ID - " + removeId);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(),"\n",e);
            System.out.println(e.getMessage());
        }
    }

    /**
     * <p>
     * This method is used to fetch all the Admin details that where stored.
     * </p>
     */
    public void fetchAllAdmins() {
        try {
            List<Admin> admins = adminService.fetchAllAdmins();
            if (!admins.isEmpty()) {
                for (Admin i : admins) {
                    System.out.println("\n-------------");
                    System.out.println(i);
                    System.out.println("-------------");
                }
            } else {
                System.out.println("No Admin Data found while fetching...");
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(),"\n",e);
            System.out.println(e.getMessage());
        }
    }
}
