package com.i2i.sms.models;

import java.util.Set ;
import java.util.HashSet ;

/**
 * <p>
 * Group class represents a Group were standard and section details are assigned. 
 * It provides methods to set and get these attributes, and also includes
 * Constructor and toString method to print the object.
 * </p>
 */
public class Group {

  private int id;
  private int standard;
  private String section;
  private Set<Student> students;
  private Set<Teacher> teachers;

  public Group() {}

  public Group(int standard, String section) {
    this.standard = standard;
    this.section = section;
  }

  public void setId(int id) {
    this.id = id;
  }
  public void setStandard(int standard) {
    this.standard = standard;
  }
  public void setSection(String section) {
    this.section = section;
  }
  public void setStudents(Set<Student> students) {
    this.students = students;
  }
  public void setTeachers(Set<Teacher> teachers) {
    this.teachers = teachers;
  }

  public int getId() {
    return id;
  }
  public int getStandard() {
    return standard;
  }
  public String getSection() {
    return section;
  }
  public Set<Student> getStudents() {
    return students;
  }
  public Set<Teacher> getTeachers() {
    return teachers;
  }

  /**
   * <p>
   * Prints the string representation of the Group Object, including the Group's data 
   * Such as standard, section using String Builder.
   * </p>
   * @return String representation of the Group Data.
   */
  public String toString() {
    StringBuilder groupData = new StringBuilder();
    groupData.append("\nGroup -- Details :\n");
    groupData.append("Group ID : ").append(id).append(", ");
    groupData.append("Standard : ").append(standard).append(", ");
    groupData.append("Section : ").append(section).append("\n");
    return groupData.toString();
  }
}
