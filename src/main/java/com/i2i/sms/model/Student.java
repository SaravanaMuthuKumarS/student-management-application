package com.i2i.sms.model;

/**
 * <p>
 * Student class represents a Student Object were id, name, standard and section are assigned.
 * It provides methods to set and get these attributes, and also includes
 * Constructor and toString method to print the object.
 * </p>
 */
public class Student {
  private int id;
  private String name;
  private int standard;
  private String section;

  public Student() {}

  public Student(String name, int standard, String section) {
    this.name = name;
    this.standard = standard;
    this.section = section;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public int getStandard() {
    return standard;
  }
  public void setStandard(int standard) {
    this.standard = standard;
  }

  public String getSection() {
    return section;
  }
  public void setSection(String section) {
    this.section = section;
  }

  /**
   * <p>
   * Prints the string representation of the Student Object, including the Student's data 
   * Such as id, name, standard and section using String Builder.
   * </p>
   * @return String representation of the Student Data.
   */
  public String toString() {
    StringBuilder student = new StringBuilder();
    student.append("Student Data : ").append("id = ").append(id)
    .append(", name = ").append(name).append(", Standard = ").append(standard)
    .append(", Section = ").append(section);
    return student.toString();
  }
}
