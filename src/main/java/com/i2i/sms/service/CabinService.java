package com.i2i.sms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.dao.CabinDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Cabin;

/**
 * <p>
 * This class provides services related to cabin management.
 * Operations like delete and fetch the cabin details from the database are performed.
 * </p>
 */
@Service
public class CabinService {
    @Autowired
    private CabinDao cabinDao;

    /**
     * <p>
     * This method is used fetch the cabin details from the database.
     * </p>
     * @return - List of Cabin objects from dao.
     * @throws StudentManagementException When error occurs in fetching process.
     */
    public List<Cabin> fetchAllCabins() throws StudentManagementException {
        return cabinDao.fetchAllCabins();
    }

    /**
     * <p>
     * This method is used search the cabin details by it's id from the database.
     * </p>
     * @param id Id of the cabin to be searched in integer type.
     * @return Cabin object from dao.
     * @throws StudentManagementException When error occurs in searching process.
     */
    public Cabin searchCabinById(int id) throws StudentManagementException {
        return cabinDao.searchCabinById(id);
    }
}