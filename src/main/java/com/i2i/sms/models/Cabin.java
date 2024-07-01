
package com.i2i.sms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * <p>
 * Cabin class represents a Cabin where TeacherName, department, and Id details are assigned.
 * It provides methods to set and get these attributes, and also includes
 * Constructors and toString method to print the object.
 * </p>
 */
@Entity
@Table(name = "cabins")
public class Cabin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "department", nullable = false)
    private String department;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Cabin() {
    }

    public Cabin(String department) {
        this.department = department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * <p>
     * Prints the string representation of the Cabin Object, including the Cabin's data
     * Such as CabinId, Teacher Name and Subject using String Builder.
     * </p>
     * @return String representation of the Cabin Data.
     */
    public String toString() {
        StringBuilder cabinData = new StringBuilder();
        cabinData.append("\nCabin -- Details :\n");
        cabinData.append("Cabin ID : ").append(id).append(", ");
        cabinData.append("Department : ").append(department).append("\n");
        return cabinData.toString();
    }
}
