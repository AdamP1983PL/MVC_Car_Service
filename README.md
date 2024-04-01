  # Car Service Application.

Car Service Application used to create service orders.  
The application allows you to read, create, edit and delete Customer Entity, Vehicle Entity and Service_Order Entity.  

## Technology stack

* Java 17
* SpringBoot 3.2.4
* MySql
* Spring Data Jpa
* Hibernate
* Thymeleaf
* HTML
* Lombok
* Gradle
* JUnit
* Mockito

## Read before You start the application

 1. The application uses the mySql database.  
 2. In MySQL Workbech create a car_service database.
 3. In the resources folder, create a database.properties file.
 4. Place the following variables in the database.properties file:  

 * mysql.host=localhost
 * mysql.port=3306
 * mysql.name=car_service
 * mysql.username={your username}
 * mysql.password={your password}
 
 5. After creating the database and starting the application for the first time, in order to pre-populate the database with data - you can use the file: resources/sql/customers_vehicles_preload.sql in MySQL Workbench.  

## Additional information

#### The application is pre-prepared for further functionalities:  
* data sorting
* creating new USER by ADMIN using Spring Security and data stored in the database
* scheduler  
* sending emails
* adding new functionalities to facilitate data management:
* searching for vehicles by customerId  
* searching for service orders by vehicleId
* searching for service orders by customerId
* searching for service orders by orderStatus
* creating statistics.  

## The application is under development.
