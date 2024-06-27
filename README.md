# Student-Management-Application

## Description :

- This application is used to manage the entire school management process from the 
student registration to the teacher management process, It has covered four entities 
such as student, group, teacher and cabin. 
- Student entity covers the basic students details and the group to which he belongs, 
Group entity covers the association of students enrolled to that group and 
teachers assigned for that class, Teacher entity contains the basics details of 
teacher their handling groups and also the cabin details allocated to them. 
- This project also contains all type of association in it, 
that is Teacher-Cabin one-to-one mapping, Teacher-Group is many-to-many mapping, and 
student-Group is many-to-one and one-to-many mapping. 
- These association with the help of Hibernate helps to handle the object and 
inserting the object to postgresql automatically to respective columns. 
- The use of Maven helps not to worry about building the project 
and dependencies required for running the project. 

## To Run the Application :

Install Maven and set Classpath for System Variable

### To check maven installation success 

- open cmd --> mvn -v --> which shows the version of maven installed

### Open cmd and run following commands :

1. mvn clean install
2. mvn exec:java -Dexec.mainClass="Main"