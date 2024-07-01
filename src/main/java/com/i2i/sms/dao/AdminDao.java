package com.i2i.sms.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Admin;
import com.i2i.sms.helper.HibernateManagement;

/**
 * <p>
 * AdminDao class uses hql queries to create, fetch and remove admin details.
 * It provides various functionalities such as accepting and adding admin details,
 * Viewing all the records that were inserted and
 * Removing admin data from the database.
 * </p>
 */
@Repository
public class AdminDao {
    private final Logger logger = LoggerFactory.getLogger(AdminDao.class);

    /**
     * <p>
     * This method is used check the admin credentials validation in the database.
     * </p>
     * @param name     Username in String format.
     * @param password password in String format
     * @return true - if Admin found false otherwise.
     * @throws StudentManagementException When error occurs in fetching admin credentials.
     */
    public boolean isAdminAvailable(String name, String password) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input to check if admin is available : admin name {}", name);
            Query query = session.createQuery("FROM Admin WHERE user_name = :username AND password = :password");
            query.setParameter("username", name);
            query.setParameter("password", password);
            return null != query.uniqueResult();
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred While searching admin Record with UserName :"
                    + name, e);
        }
    }

    /**
     * <p>
     * This method is used insert the Admin details into the database.
     * </p>
     * @param admin Admin object.
     * @return admin - Admin object.
     * @throws StudentManagementException When error occurs in insertion process.
     */
    public Admin createAdmin(Admin admin) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input : Admin name {} to add in the database", admin.getUserName());
            transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
            return admin;
        } catch (Exception e) {
            HibernateManagement.rollbackTransaction(transaction);
            throw new StudentManagementException("Error Occurred While inserting Admin Record for "
                    + "Admin : " + admin.getUserName(), e);
        }
    }

    /**
     * <p>
     * This method is used remove the admin details by Id from the database.
     * </p>
     *
     * @param id id of the admin to be removed in integer type.
     * @throws StudentManagementException When error occurs in deletion process.
     * @return true when removed false otherwise.
     */
    public boolean removeAdminById(int id) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input : Admin id {} to remove in the database", id);
            transaction = session.beginTransaction();
            Admin admin = session.get(Admin.class, id);
            if (null != admin) {
                session.delete(admin);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            HibernateManagement.rollbackTransaction(transaction);
            throw new StudentManagementException("Error Occurred While deleting Admin Record with Id : "
                    + id, e);
        }
        return false;
    }

    /**
     * <p>
     * This method is used fetch the admin details from the database.
     * </p>
     *
     * @return - List of Cabin objects.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public List<Admin> fetchAllAdmins() throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Process started : Fetching all the admins from database");
            return session.createQuery("From Admin", Admin.class).list();
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred While Fetching the Admin Records ", e);
        }
    }
}
