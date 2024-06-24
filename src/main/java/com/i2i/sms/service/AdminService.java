package com.i2i.sms.service;

import com.i2i.sms.dao.AdminDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.models.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminService {
    private AdminDao adminDao = new AdminDao();

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
        return adminDao.createAdmin(admin);
    }

    /**
     * <p>
     * This method is used to remove the Admin data by Id from the database.
     * </p>
     * @param id Id of the Admin to be removed in integer type.
     * @throws StudentManagementException When error occurs in deletion process.
     * @retrun true when removed false otherwise.
     */
    public boolean removeAdminById(int id) throws StudentManagementException {
        return adminDao.removeAdminById(id);
    }
}
