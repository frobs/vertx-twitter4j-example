# Twitter4j + VertX = Twitter4X

This project is a functional example about how to create a full VertX project with connection database etc... and Twitter4j library

## Components
* VertX 3.2.1
* Twitter4j 4.0.5
* Gradle 2.13
* MySql

## Instructions

### Install gradle wrapper
`./gradlew` or `gradlew.bat`

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




