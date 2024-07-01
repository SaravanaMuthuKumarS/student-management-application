package com.i2i.sms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.dao.AdminDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Admin;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;
    private final Logger logger = LoggerFactory.getLogger(AdminService.class);


    /**
     * <p>
     * This method is used to check admin credentials presence in database.
     * </p>
     * @param userName Username in String format.
     * @param password  Password in String format.
     * @return true if admin present false otherwise.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public boolean isAdminAvailable(String userName, String password)
            throws StudentManagementException {
        return adminDao.isAdminAvailable(userName, password);
    }

    /**
     * <p>
     * This method is used insert the Admin details into the database.
     * </p>
     * @param userName Username in String format.
     * @param password  Password in String format.
     * @return admin - Admin object.
     * @throws StudentManagementException When error occurs in insertion process.
     */
    public Admin createAdmin(String userName, String password) throws StudentManagementException {
        Admin admin = new Admin(userName, password);
        logger.debug("Process started : Admin object for : {} created and sent for insertion in database.", admin.getUserName());
        return adminDao.createAdmin(admin);
    }

    /**
     * <p>
     * This method is used to remove the Admin data by Id from the database.
     * </p>
     * @param id id of the Admin to be removed in integer type.
     * @throws StudentManagementException When error occurs in deletion process.
     * @return true when removed false otherwise.
     */
    public boolean removeAdminById(int id) throws StudentManagementException {
        return adminDao.removeAdminById(id);
    }

    /**
     * <p>
     * This method is used fetch the Admin details from the database.
     * </p>
     * @return - List of Cabin objects from dao.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public List<Admin> fetchAllAdmins() throws StudentManagementException {
        return adminDao.fetchAllAdmins();
    }
}
