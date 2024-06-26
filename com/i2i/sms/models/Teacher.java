package com.i2i.sms.models;

import java.util.Set ;
import java.util.HashSet ;

/**
 * <p>
 * Teacher class represents a teachers object were Teacher Id, name and 
 * Subject details are assigned. 
 * It provides methods to set and get these attributes, and also includes 
 * Constructor methods and toString method to print the object.
 * </p>
 */
public class Teacher {

  private int id;
  private String name;
  private String subject;
  private Cabin cabin;
  private Set<Group> groups;
 
  public Teacher() {}
 
  public Teacher(String name, String subject) {
    this.name = name;
    this.subject = subject;
  }

  public void setId(int id) {
    this.id = id;
  }
  public void setName(String name ) {
    this.name = name;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  public void setCabin(Cabin cabin) {
    this.cabin = cabin;
  }
  public void setGroups(Set<Group> groups) {
    this.groups = groups;
  }

  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getSubject() {
    return subject;
  }
  public Cabin getCabin() {
    return cabin;
  }
  public Set<Group> getGroups() {
    return groups;
  }

  /**
   * <p>
   * Prints the string representation of the Teacher Object, including the Teacher's data such as 
   * Id, teacher name and handling subject using String Builder.
   * @return String representation of the Teacher Data.
  **/
  public String toString() {
    StringBuilder teacherData = new StringBuilder();
    teacherData.append("\nTeacher -- Details :\n");
    teacherData.append("Teacher ID : ").append(id).append(", ");
    teacherData.append("Teacher Name : ").append(name).append(", ");
    teacherData.append("Subject Handling : ").append(subject).append("\n");
    return teacherData.toString();
  }
}
