# Examples of Inversion of Control/Dependency Injection In Java

There are slides and notes included in this project in LibreOffce format.

## Overview
There are 4 submodule projects and 1 common code submodule.

1. spring - DI/IoC using Spring
1. guice - DI using Google Guice
1. cdi - DI using CDI and Apache DeltaSpike
1. dagger - DI using Google's Dagger 2

## Prerequisites
1. Java >= 6
1. Maven >= 3.0
1. Internet Access for downloading dependencies via Maven

## Compiling

```
mvn compile
```

## Running Tests
```
mvn test
```

## Database
The database used in each of the submodules is HSQLDB and uses
[Liquibase](http://www.liquibase.org/) to initialize the database 
schema.