# 5.3.0 - Spring Security (Work in Progress)
(Please view previous branches for other topics)
Note: this will not be using Spring boot as of now. I wanted to focus on each topic with as mininal auto config as possible
Note: The version number here is behind on purpose. I jumped ahead a bit to learn Spring Batch so the version number had to reflect the changes.

## Prereqs for this build
- Java 1.8
- Gradle 4.6 ( Needed for Gretty plugin)
- MySQL

## Tools for this project
- Intellij Idea
- DataGrip
- 

## Usage
Testing
```text
gradle appRun
```
Building
```text
gradle clean build
```
  
## Updates to the project from this repo
- Registration Process:
    - Register a new user at endpoint: /register
- Spring Security
- Spring Data
- Gretty Plugin for gradle
- Gradle 4.6 as the build system
    I created a separate client side repo for the front end. I wanted to explore React / Redux and get better with JavaScript I'm working on building my current development model based around a good Java webservice. So check out my [other repo](https://github.com/NinjaLogix/React-Redux-Example)
- Finally gave a more descriptive readme doc
    
## Articles relevant to this branch:
[gretty plugin](http://akhikhl.github.io/gretty-doc/Getting-started.html)<br/>
[Spring Data](https://spring.io/guides/gs/accessing-data-jpa/)<br/>