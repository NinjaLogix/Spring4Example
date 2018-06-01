# <span style="color:green">5.3.0</span> - Spring Security
(Please view previous branches for other topics)<br/>
Note: this will not be using Spring boot as of now. I wanted to focus on each topic with as mininal auto config as possible
Note: The version number here is behind on purpose. I jumped ahead a bit to learn Spring Batch so the version number had to reflect the changes.

## Prereqs for this build
- Java 1.8
- Gradle 4.6 ( Needed for [Gretty](http://akhikhl.github.io/gretty-doc/Getting-started.html)
 plugin)
- MySQL

## Recommended Tools for this project
- Intellij Idea
- PostMan for API calls

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
- Spring Data with hibernate relation mapping for entities
- Gretty Plugin for testing
- Gradle 4.6 as the build system
- React / Redux client app
    - I created a separate client side repo for the front end. I wanted to explore React / Redux and get better with JavaScript I'm working on building my current development model based around a good Java webservice. So check out [React-Redux-Example](https://github.com/NinjaLogix/React-Redux-Example)
- Finally gave a more descriptive readme doc
    
## Articles relevant to this branch:
`Note:` ___I'm not following these articles to the T for say, but more or less learning from them and making adaptations for my purposes along the way. The main reason I'm updating this repo is for learning purposes so there will always be more than one topic involved.___<br/>
[Gretty plugin](http://akhikhl.github.io/gretty-doc/Getting-started.html)<br/>
[Spring Data](https://spring.io/guides/gs/accessing-data-jpa/)<br/>
[Spring Security](http://www.baeldung.com/security-spring)