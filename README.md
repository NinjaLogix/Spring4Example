# 5.3.0 - Spring Security (Work in Progress)
(Please view previous branches for other topics)
Note: this will not be using Spring boot as of now. I wanted to focus on each topic with as mininal auto config as possible

## Prereqs that need to be installed
- Java 1.8
- Gradle 4.6 ( Needed for Gretty plugin)
- MySQL
- Preferred IDE, I use intellij personally

## Usage
Testing
```text
gradle appRun
```
Building
```text
gradle clean build
```

## What's New
- Created separate repo for client side for this service. Wanted to use another repo just to keep things separate because I want to expand on React and Redux.
- Moved the build system to Gradle because it is becoming more popular and accepting more languages as it progresses. Took the time to get familiar with it so that I can teach others and teach myself.
- Started using Spring Data to eliminate some DAO implementation with Hibernate
  
## Updates to the project
- Registration Process:
    - Register a new user at endpoint: /register
    
## Articles relevant to this branch:
[gretty plugin](http://akhikhl.github.io/gretty-doc/Getting-started.html)<br/>
[Spring Data](https://spring.io/guides/gs/accessing-data-jpa/)<br/>