package com.i2i.sms.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Admin;
import com.i2i.sms.helper.HibernateManagement;

import java.util.List;

public class AdminDao {
    /**
     * <p>
     * This method is used check the admin credentials validation in the database.
     * </p>
     * @param name Username in String format.
     * @param password  password in String format
     * @return true - if Admin found false otherwise.
     * @throws StudentManagementException When error occurs in fetching admin credentials.
     */
    public boolean isAdminAvailable(String name, String password) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Admin WHERE user_name = :username AND password = :password");
            query.setParameter("username", name);
            query.setParameter("password", password);
            return null != query.uniqueResult();
        } catch (Exception e) {
            throw new StudentManagementException("Error Occured While searching admin Record with UserName :"
                    + name);
        }
    }

    /**
     * <p>
     * This method is used insert the Admin details into the database.
     * </p>
     * @param admin
     *       Admin object.
     * @return admin - Admin object.
     * @throws StudentManagementException When error occurs in insertion process.
     */
    public Admin createAdmin(Admin admin) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
            return admin;
        } catch (Exception e) {
            HibernateManagement.rollbackTransaction(transaction);
            e.printStackTrace();
            throw new StudentManagementException("Error Occured While inserting Admin Record for "
                    + "Admin : " + admin.getUserName());
        }
    }

    /**
     * <p>
     * This method is used remove the admin details by Id from the database.
     * </p>
     * @param id Id of the admin to be removed in integer type.
     * @throws StudentManagementException When error occurs in deletion process.
     * @retrun true when removed false otherwise.
     */
    public boolean removeAdminById(int id) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Admin admin = session.get(Admin.class, id);
            if (null != admin) {
                session.delete(admin);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            HibernateManagement.rollbackTransaction(transaction);
            throw new StudentManagementException("Error Occured While deleting Admin Record with Id : "
                    + id);
        }
        return false;
    }

    /**
     * <p>
     * This method is used fetch the admin details from the database.
     * </p>
     * @return - List of Cabin objects.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public List<Admin> fetchAllAdmins() throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            return session.createQuery("From Admin", Admin.class).list();
        } catch (Exception e) {
            throw new StudentManagementException("Error Occured While Fetching the Admin Records ");
        }
    }
}
