package com.i2i.sms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.dao.GroupDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Group;

/**
 * <p>
 * This class provides services related to group management.
 * Operations like insert, delete, search and fetch
 * The group details from the database are performed.
 * </p>
 */
@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;
    private final Logger logger = LoggerFactory.getLogger(GroupService.class);

    /**
     * <p>
     * This method is used to add or fetch a group data in database.
     * </p>
     * @param standard standard of the student in integer type.
     * @param section  section of the student in String type.
     * @return Group object from dao.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public Group getOrCreateGroup(int standard, String section)
            throws StudentManagementException {
        Group group = new Group(standard, section);
        logger.debug("Process started : Group object for: {} - {} created and sent for insertion in database.", group.getStandard(), group.getSection());
        return groupDao.getOrCreateGroup(group);
    }

    /**
     * <p>
     * This method is used to search the group data by it's id from the database.
     * </p>
     * @param id id of the student to be searched in integer type.
     * @return Group object from dao.
     * @throws StudentManagementException When error occurs in searching process.
     */
    public Group searchGroupById(int id) throws StudentManagementException {
        return groupDao.searchGroupById(id);
    }

    /**
     * <p>
     * This method is used to fetch the group details from the database.
     * </p>
     * @return List of Group objects from dao.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public List<Group> fetchAllGroups() throws StudentManagementException {
        return groupDao.fetchAllGroups();
    }

    /**
     * <p>
     * This method is used to remove the group data by Id from the database.
     * </p>
     * @param id id of the group to be removed in integer type.
     * @throws StudentManagementException When error occurs in deletion process.
     * @return true when removed false otherwise.
     */
    public boolean removeGroupById(int id) throws StudentManagementException {
        return groupDao.removeGroupById(id);
    }
}