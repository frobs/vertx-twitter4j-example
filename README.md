# Twitter4j + VertX = Twitter4X

This project is a functional example about how to create a full VertX project with connection database etc... and Twitter4j library

## Components
* VertX 3.2.1
* Twitter4j 4.0.5
* Gradle 2.13
* MySql

## You can find here:
* VertX example with more than one class.
* JDBC connection to database.
* Twitter4J, real usage, with login, token persistance and timeline.
* Gradle:
  * Generate autoexecutable vertX jar with all dependencies inside.
  * Task for execute app in hot-reload mode. You don't need stop-start everytime your java code change.
  * Task for create database.
  * Task for create tables inside database.
  * Several configuration environments option.

## Instructions

### Install gradle wrapper
`./gradlew` or `gradlew.bat`

### We have configuration by environment
Now we have two configrations development, and test. This configuration modify some options in build.gradle file.

The default mode is development, we can change this adding `-Penv=mode` to gradle command. Example: `gradle -Penv=test createDatabase` this will create our test database. 

### Create database
`./gradlew createDatabase`

### Create tables
`./gradlew createTables`

### Compile
`./gradlew shadowJar`

### Compile and run
`./gradlew runShadow`

### Run with auto-reload
`./gradlew run`


## Api
http://localhost:8080/twitter/timeline/?username=twitter_user // Twitter user timeline

##TODO
* Integration with some CI System (maybe Travis)
* Wrap twitter4j code inside a proper class ( We shouldn't use alien code mixed with our code, What can we do if twitter4j api change?)
* Add Integration test, test routes with http client
* Add a interface with MVC client ( Maybe Angular 2 or React...any suggestion?)
* Add acceptance tests with selenium
* Change unite test to Spock instead of JUnit
* Automate test run
* Add Aceptation Test
* 


