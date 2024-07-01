
package com.i2i.sms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * Cabin class represents a Cabin where TeacherName, department, and Id details are assigned.
 * It provides methods to set and get these attributes, and also includes
 * Constructors and toString method to print the object.
 * </p>
 */
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    public Admin() {
    }

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String  password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    /**
     * <p>
     * Prints the string representation of the Cabin Object, including the Cabin's data
     * Such as CabinId, Teacher Name and Subject using String Builder.
     * </p>
     * @return String representation of the Cabin Data.
     */
    public String toString() {
        StringBuilder adminData = new StringBuilder();
        adminData.append("\nAdmin -- Details :\n");
        adminData.append("Admin ID : ").append(id).append(", ");
        adminData.append("Username : ").append(userName).append("\n");
        return adminData.toString();
    }
}
