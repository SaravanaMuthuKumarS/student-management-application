package com.i2i.sms.service;

import java.util.List;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.dao.StudentDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Group;
import com.i2i.sms.models.Student;

/**
 * <p>
 * This class provides services related to student management.
 * Operations like insert, delete, search and fetch
 * The student details from the database are performed.
 * </p>
 */
@Service
public class StudentService {
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentDao studentDao;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    /**
     * <p>
     * This method is used insert the student details into the database.
     * </p>
     * @param name  name of the student in String format.
     * @param dob   dob of the student in Date format.
     * @param group group object of the student to which he belongs.
     * @return Student object from dao.
     * @throws StudentManagementException When error occurs in insertion process.
     */
    public Student addStudent(String name, LocalDate dob, Group group)
            throws StudentManagementException {
        Student student = new Student(name, dob);
        student.setGroup(group);
        logger.debug("Process started : Student object for: Name - {}, DOB - {} created and sent for insertion in database.", student.getName(), student.getDob());
        return studentDao.createStudent(student);
    }

    /**
     * <p>
     * This method is used to fetch all the students details from the database.
     * </p>
     * @return List of Student objects from dao.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public List<Student> fetchAllStudents() throws StudentManagementException {
        return studentDao.fetchAllStudents();
    }

    /**
     * <p>
     * This method is used to search the students detail by it's id from the database.
     * </p>
     * @param id id of the student to be searched in integer type.
     * @return Student object from dao.
     * @throws StudentManagementException When error occurs in Search process.
     */
    public Student searchStudentById(int id) throws StudentManagementException {
        return studentDao.searchStudentById(id);
    }

    /**
     * <p>
     * This method is used to remove the students detail by Id from the database.
     * </p>
     * @param id id of the student to be removed in integer type.
     * @throws StudentManagementException When error occurs in deletion process.
     * @return true when removed false otherwise.
     */
    public boolean removeStudentById(int id) throws StudentManagementException {
        return studentDao.removeStudentById(id);
    }

    /**
     * <p>
     * This method is used to add or fetch a group data in database.
     * </p>
     * @param standard standard of the student in integer type.
     * @param section  section of the student in String type.
     * @return Group object from dao.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public Group getOrCreateGroup(int standard, String section) throws StudentManagementException {
        return groupService.getOrCreateGroup(standard, section);
    }
}