package com.i2i.sms.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.models.Cabin;

/**
 * <p>
 * CabinDao class uses hql to fetch and remove cabin details.
 * It provides various functionalities such as viewing all the records that were inserted,
 * Searching for cabin details from the database.
 * </p>
 */
public class CabinDao {

    /**
     * <p>
     * This method is used fetch the cabin details from the database.
     * </p>
     * @return - List of Cabin objects.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public List<Cabin> fetchAllCabins() throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            return session.createQuery("From Cabin", Cabin.class).list();
        } catch (Exception e) {
            throw new StudentManagementException("Error Occured While Fetching the Cabin Records ");
        }
    }

    /**
     * <p>
     * This method is used search the cabin details by it's id from the database.
     * </p>
     * @param id Id of the cabin to be searched in integer type.
     * @return cabin - Cabin object.
     * @throws StudentManagementException When error occurs in searching process.
     */
    public Cabin searchCabinById(int id) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            Cabin cabin = session.get(Cabin.class, id);
            Hibernate.initialize(cabin.getTeacher());
            return cabin;
        } catch (Exception e) {
            throw new StudentManagementException("Error Occured While Searching Cabin Record with Id : "
                    + id);
        }
    }
}
