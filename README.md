# ParkaLot-COMP-1471
A repository to hold our code of the application created for the group project for COMP-1471 of the University of Greenwich's MSc Computer Science

## How to set up for development
### Backend
#### Dependencies
You will need: 
 - Java 25 JDK
 - PostgreSQL
 - An IDE capable of running Java

#### Instructions
To set up for development in the backend you'll need to install the Java JDK and an appropriate IDE to develop and run your code. 
 
To be able to successfully call the database you'll need to install PostgreSQL from: https://www.postgresql.org/

Install it with the defaults and you'll have installed "pgAdmin4" an application to help manage PostgreSQL databases. After initial setup you can then create a new database (it's set as `url: jdbc:postgresql://localhost:5432/dev_parkalot` in the `application.yaml` file, where `dev_parkalot` is the name of the database so you need to consider this). 

Once created run the script located in `/backend/src/main/resources/DatabaseCreateScript.pgsql` to add the tables we'll need as well as some initial seed data.

At this point you can now run the application.  
To run and test your code without an IDE you can also use the `./mvnw` script included in the source code through the command line.
 - `./mvnw spring-boot:run` will start the project.
 - `./mvnw test` will run all tests.

### Frontend
