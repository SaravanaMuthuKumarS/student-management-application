package com.i2i.sms.model;

public class Student {
  private int id;
  private String name;
  private String dob;

  public Student() {}

  public Student(String name, String dob) {
    this.name = name;
    this.dob = dob;
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

  public String getDob() {
    return dob;
  }
  public void setDob(String dob) {
    this.dob = dob;
  }

  public String toString() {
    StringBuilder student = new StringBuilder();
    student.append("Student Data : ").append("id = ").append(id)
    .append(", name = ").append(name).append(", dob = ").append(dob);
    return student.toString();
  }
}
