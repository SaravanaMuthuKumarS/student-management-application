package com.i2i.sms.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.i2i.sms.utils.DateUtils;

/**
 * <p>
 * Student class represents a Student Object where id, name, dob, and
 * Group object details are assigned.
 * It provides methods to set and get these attributes, and also includes
 * Constructor and toString method to print the object.
 * </p>
 */
@Entity
@Table(name = "students")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "dob", nullable = false)
  private LocalDate dob;

  @ManyToOne(fetch = javax.persistence.FetchType.EAGER)
  @JoinColumn(name = "group_id", nullable = false)
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