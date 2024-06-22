package com.i2i.sms.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

/**
 * <p>
 * Group class represents a Group where standard and section details are assigned.
 * It provides methods to set and get these attributes, and also includes
 * Constructor and toString method to print the object.
 * </p>
 */
@Entity
@Table(name = "groups")
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "standard", nullable = false)
  private int standard;

  @Column(name = "section", nullable = false)
  private String section;

  @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Student> students = new HashSet<>();

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
          name = "group_teacher",
          joinColumns = @JoinColumn(name = "group_id"),
          inverseJoinColumns = @JoinColumn(name = "teacher_id")
  )
  private Set<Teacher> teachers = new HashSet<>();

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