import java.util.Scanner;

import com.i2i.sms.controller.CabinController;
import com.i2i.sms.controller.GroupController;
import com.i2i.sms.controller.StudentController;
import com.i2i.sms.controller.TeacherController;

/**
 * <p>
 * Main class handles the interaction between the user and the respective
 * Controllers for performing operations accordingly.
 * It provides various functionalities such as adding details
 * Viewing all the records that were inserted, 
 * Search for details, and removing data from the database.
 * </p>
 */
public class Main {

  private static Scanner scanner = new Scanner(System.in);
  private static CabinController cabinController = new CabinController();
  private static GroupController groupController = new GroupController();
  private static StudentController studentController = new StudentController();
  private static TeacherController teacherController = new TeacherController();

  /**
   * <p>
   * Validates admin credentials by checking the username and password that user enters.
   * </p>
   * @return true if credentials are valid, otherwise false.
   */
  public static boolean adminValidate() {
    System.out.println("\nEnter UserName :");
    String userName = scanner.next();
    System.out.println("\nEnter Password :");
    int password = scanner.nextInt();
    System.out.print("\n");
    return userName.equals("admin") && password == 123;
  }

  public static void main(String[] args) {
    boolean exit = true;
    int choice;
    while (exit) {
      System.out.println("\n");
      System.out.println("\t\t\tWelcome to School Management Application : ");
      System.out.println("\t\tFollow the Menu to proceed with your processing :");
      System.out.println("\tEnter your choice :-");
      System.out.println("1. Create a New School Data");
      System.out.println("2. Fetch School Data ( Admin Purpose Only )");
      System.out.println("3. Search for Particular Data by their Id");
      System.out.println("4. Remove a Particular School Data ( Admin Purpose Only )");
      System.out.println("5. Exit\n");
      choice = scanner.nextInt();
      switch (choice) {
        case 1:
          System.out.println("\n 1-->Create a Student Data");
          System.out.println("\n 2-->Create a Group Data ");
          System.out.println("\n 3--> Create a Teacher Data\n");
          System.out.println("\n -----> Press Any key and Enter for Previous Menu\n");
          int access = scanner.nextInt();
          System.out.println();
          switch (access) {
            case 1:
              studentController.createStudent();
              break;
            case 2:
              if (adminValidate()) {
                groupController.createGroup();
              }
              break;
            case 3:
              if (adminValidate()) {
                teacherController.createTeacher();
              }
              break;
            default:
              System.out.println("Previous Menu...");
              break;
          }
          break;
        case 2:
          if (adminValidate()) {
            System.out.println("\n 1--> Fetch the Student Details");
            System.out.println("\n 2--> Fetch the Group Details");
            System.out.println("\n 3--> Fetch the Teacher Details");
            System.out.println("\n 4--> Fetch the Cabin Details\n");
            System.out.println("\n -----> Press Any key and Enter for Previous Menu\n");
            choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
              case 1:
                studentController.fetchAllStudents();
                break;
              case 2:
                groupController.fetchAllGroups(); 
                break;
              case 3:
                teacherController.fetchAllTeachers();
                break;
              case 4:
                cabinController.fetchAllCabins();
                break;
              default:
                System.out.println("Previous Menu...");
                break;
            }
          } else {
            System.out.println("Invalid User-Id and Password");
          } 
          break;
        case 3:
          System.out.println("\n 1--> Search a Student Details");
          System.out.println("\n 2--> Search a Group Details ( Admin Purpose Only )");
          System.out.println("\n 3--> Search a Teacher Details ( Admin Purpose Only )");
          System.out.println("\n 4--> Search a Cabin Details ( Admin Purpose Only )\n");
          System.out.println("\n -----> Press Any key and Enter for Previous Menu\n");
          choice = scanner.nextInt();
          System.out.println();
          switch (choice) {
            case 1:
              studentController.searchStudentById();
              break;
            case 2:
              if (adminValidate()) {
                groupController.searchGroupById();
              } else {
                System.out.println("Invalid User-Id and Password");
              }
              break;
            case 3:
              if (adminValidate()) {
                teacherController.searchTeacherById();
              } else {
                System.out.println("Invalid User-Id and Password");
              }
              break;
            case 4:
              if (adminValidate()) {
                cabinController.searchCabinById();
              } else {
                System.out.println("Invalid User-Id and Password");
              }
              break;
            default:
              System.out.println("Previous Menu...");
              break;
          }
          break;
        case 4:
          if (adminValidate()) {
            System.out.println("\n 1--> Remove a Student Details");
            System.out.println("\n 2--> Remove a Group Detail");
            System.out.println("\n 3--> Remove a Teacher Detail\n");
            System.out.println("\n -----> Press Any key and Enter for Previous Menu\n");
            System.out.println();
            choice= scanner.nextInt();
            switch (choice) {
              case 1:
                studentController.removeStudentById();
                break;
              case 2:
                groupController.removeGroupById();
                break;
              case 3:
                teacherController.removeTeacherById();
                break;
              default:
                System.out.println("Previous Menu...");
                break;
            }
          } else {
            System.out.println("Invalid User-Id and Password");
          } 
          break;
        case 5:
          System.out.println("Exiting...");
          exit = false;
          break;
        default:
          System.out.println("Invalid Choice...");
          break;
      }
    }     
  }
}