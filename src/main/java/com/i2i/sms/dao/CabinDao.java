package com.i2i.sms.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
@Repository
public class CabinDao {
    private final Logger logger = LoggerFactory.getLogger(CabinDao.class);

    /**
     * <p>
     * This method is used fetch the cabin details from the database.
     * </p>
     * @return - List of Cabin objects.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public List<Cabin> fetchAllCabins() throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Process started : Fetching all the cabins from database");
            return session.createQuery("From Cabin", Cabin.class).list();
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred While Fetching the Cabin Records", e);
        }
    }

    /**
     * <p>
     * This method is used search the cabin details by it's id from the database.
     * </p>
     * @param id id of the cabin to be searched in integer type.
     * @return cabin - Cabin object.
     * @throws StudentManagementException When error occurs in searching process.
     */
    public Cabin searchCabinById(int id) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input: Cabin id {} to search in the database", id);
            Cabin cabin = session.get(Cabin.class, id);
            Hibernate.initialize(cabin.getTeacher());
            return cabin;
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred While Searching Cabin Record with Id : "
                    + id, e);
        }
    }
}
