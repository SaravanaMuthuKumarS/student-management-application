package com.i2i.sms.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.models.Student;

/**
 * <p>
 * StudentDao class uses hql queries to create, fetch, search and remove student details.
 * It provides various functionalities such as accepting and adding student details,
 * Viewing all the records that were inserted, searching for students details, and
 * Removing student data from the database.
 * </p>
 */
@Repository
public class StudentDao {
    private final Logger logger = LoggerFactory.getLogger(StudentDao.class);

    /**
     * <p>
     * This method is used search the students details by it's id from the database.
     * </p>
     * @param id id of the student in integer type.
     * @return student - Student object.
     * @throws StudentManagementException When error occurs in Search process.
     */
    public Student searchStudentById(int id) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input: Student id {} to search in the database", id);
            Student student = session.get(Student.class, id);
            return student;
        } catch (Exception e) {
            throw new StudentManagementException("Error Occured While searching Student Record with ID "
                    + id, e);
        }
    }

    /**
     * <p>
     * This method is used insert the student details into the database.
     * </p>
     * @param student Student object.
     * @return student - student object.
     * @throws StudentManagementException When error occurs in insertion process.
     */
    public Student createStudent(Student student) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input: Student {} to add in the database", student);
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            HibernateManagement.rollbackTransaction(transaction);
            e.printStackTrace();
            throw new StudentManagementException("Error Occured While inserting Student Record for "
                    + "student : " + student.getName(), e);
        }
    }

    /**
     * <p>
     * This method is used remove the students details by Id from the database.
     * </p>
     * @param id id of the student to be removed in integer type.
     * @throws StudentManagementException When error occurs in deletion process.
     * @retrun true when removed false otherwise.
     */
    public boolean removeStudentById(int id) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input: Student id {} to remove from the database", id);
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                student.getGroup().getStudents().remove(student);
                session.delete(student);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            HibernateManagement.rollbackTransaction(transaction);
            throw new StudentManagementException("Error Occured While deleting Student Record "
                    + "with id : " + id, e);
        }
        return false;
    }

    /**
     * <p>
     * This method is used fetch all the students details from the database.
     * </p>
     * @return students - List of Student objects.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public List<Student> fetchAllStudents() throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Process started : Fetch all the students from database");
            List<Student> students = session.createQuery("From Student", Student.class).list();
            return students;
        } catch (Exception e) {
            throw new StudentManagementException("Error Occured While Fetching Students Record ", e);
        }
    }
}
