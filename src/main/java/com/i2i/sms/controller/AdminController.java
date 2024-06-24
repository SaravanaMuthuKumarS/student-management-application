package com.i2i.sms.controller;

import java.util.Scanner;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Admin;
import com.i2i.sms.service.AdminService;

public class AdminController {
    private AdminService adminService = new AdminService();
    private static Scanner scanner = new Scanner(System.in);

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
            System.out.println(e.getMessage());
        }
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
            Admin admin = adminService.createAdmin(user,key);
            System.out.println("Admin added successfully.");
            System.out.println("\n" + admin);
        } catch (StudentManagementException e) {
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
            System.out.println(e.getMessage());
        }
    }

}
