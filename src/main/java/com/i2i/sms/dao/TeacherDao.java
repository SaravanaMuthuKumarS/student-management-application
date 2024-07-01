package com.i2i.sms.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.models.Teacher;

/**
 * <p>
 * TeacherDao class uses hql queries to create, fetch, search and remove teacher details.
 * It provides various functionalities such as accepting and adding teacher details,
 * Viewing all the records that were inserted,
 * Searching for teacher details,  and removing teacher data from the database.
 * </p>
 */
@Repository
public class TeacherDao {
    private final Logger logger = LoggerFactory.getLogger(TeacherDao.class);

    /**
     * <p>
     * This method is used insert the teacher details into the database.
     * </p>
     * @param teacher Teacher object.
     * @throws StudentManagementException When error occurs in insertion process.
     */
    public Teacher saveTeacher(Teacher teacher) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input: Teacher {} to add in the database", teacher);
            transaction = session.beginTransaction();
            session.save(teacher);
            transaction.commit();
            return teacher;
        } catch (Exception e) {
            HibernateManagement.rollbackTransaction(transaction);
            throw new StudentManagementException("Error Occurred While inserting teacher record : "
                    + teacher.getName(), e);
        }
    }

    /**
     * <p>
     * This method is used search the teacher details by it's id from the database.
     * </p>
     * @param id id of the teacher in integer type.
     * @return teacher - Teacher object.
     * @throws StudentManagementException When error occurs in searching process.
     */
    public Teacher searchTeacherById(int id) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input: Teacher id {} to search in the database", id);
            Teacher teacher = session.get(Teacher.class, id);
            Hibernate.initialize(teacher.getCabin());
            Hibernate.initialize(teacher.getGroups());
            return teacher;
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred While Searching teacher record with Id "
                    + id, e);
        }
    }

    /**
     * <p>
     * This method is used remove the teacher details by Id from the database.
     * </p>
     * @param id id of the teacher to be removed in integer type.
     * @throws StudentManagementException When error occurs in deletion process.
     */
    public boolean removeTeacherById(int id) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input: Teacher id{} to remove from the database", id);
            transaction = session.beginTransaction();
            Teacher Teacher = session.get(Teacher.class, id);
            if (Teacher != null) {
                session.delete(Teacher);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            HibernateManagement.rollbackTransaction(transaction);
            throw new StudentManagementException("Error Occurred While deleting teacher record with Id : "
                    + id, e);
        }
        return false;
    }

    /**
     * <p>
     * This method is used fetch the teacher details from the database.
     * </p>
     * @return teachers - List of teacher objects.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public List<Teacher> fetchAllTeachers() throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Process started : Fetching all the teachers from database");
            return session.createQuery("From Teacher", Teacher.class).list();
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred While fetching teacher records ", e);
        }
    }
}