To Run the Application :
---> Install Maven and set Classpath for System Variable

To check maven installation success -- open cmd --> mvn -v --> shows the version of maven installed

Open cmd and run following commands :

mvn clean install
mvn exec:java -Dexec.mainClass="com.i2i.sms.controller.CommonController"