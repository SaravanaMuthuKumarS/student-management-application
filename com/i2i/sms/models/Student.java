package com.i2i.sms.models;

import java.time.LocalDate;

import com.i2i.sms.utils.DateUtils;

/**
 * <p>
 * Student class represents a Student Object were id, name, dob and 
 * Group object details are assigned.
 * It provides methods to set and get these attributes, and also includes
 * Constructor and toString method to print the object.
 * </p>
 */
public class Student {

  private int id;
  private String name;
  private LocalDate dob;
  private Group group;
 
  public Student() {}

  public Student(String name, LocalDate dob) {
    this.name = name;
    this.dob = dob;
  }

  public void setId(int id) {
    this.id = id;
  }
  public void setName(String name ) {
    this.name = name;
  }
  public void setDob(LocalDate dob) {
    this.dob = dob;
  }
  public void setGroup(Group group) {
    this.group = group;
  }

  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public LocalDate getDob() {
    return dob;
  }
  public Group getGroup() {
    return group;
  }
   
  /**
   * <p>
   * Prints the string representation of the Student Object, including the Student's data 
   * Such as id, name, date of birth and age using String Builder.
   * </p>
   * @return String representation of the Student Data.
   */
  public String toString() {
    StringBuilder studentData = new StringBuilder();
    studentData.append("\nStudent Details :\n");
    studentData.append("Student ID : ").append(id).append(", ");
    studentData.append("Student Name : ").append(name).append(", ");
    studentData.append("Student DOB : ").append(dob).append(", ");
    studentData.append("Student Age: ").append(DateUtils.findDifference(dob)).append("\n");
    return studentData.toString();
  }
 
}
