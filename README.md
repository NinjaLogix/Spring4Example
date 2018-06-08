# <span style="color:green">5.3.0</span> - Spring Security
[![Release](https://img.shields.io/github/release/NinjaLogix/Spring4Example.svg?style=for--the--badge)](https://github.com/NinjaLogix/Spring4Example/releases)
[![Changelog](https://img.shields.io/badge/changelog-wiki-lightgrey.svg?style=for--the--badge)](https://github.com/NinjaLogix/Spring4Example/wiki)
[![Build Status](https://travis-ci.org/NinjaLogix/Spring4Example.svg?branch=5.3.0-Spring_Security)](https://travis-ci.org/NinjaLogix/Spring4Example)
<br/><br/>
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
Setup for IntelliJ (converting from maven project)<br/>
`Note:` ___If your importing this as a gradle project then you don't need to do these steps___
```text
./gradlew cleanIdea
./gradlew idea
```
Testing
```text
./gradlew appRun
```
Building
```text
./gradlew clean build
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
- Added [travis-ci](https://travis-ci.org/) support.
- Added [shields](https://shields.io/) to readme to better describe the project
    
## Articles relevant to this branch:
`Note:` ___I'm not following these articles to the T for say, but more or less learning from them and making adaptations for my purposes along the way. The main reason I'm updating this repo is for learning purposes so there will always be more than one topic involved.___<br/>
[Gretty plugin](http://akhikhl.github.io/gretty-doc/Getting-started.html)<br/>
[Spring Data](https://spring.io/guides/gs/accessing-data-jpa/)<br/>
[Spring Security](http://www.baeldung.com/security-spring)<br/>
[Logback](http://www.baeldung.com/logback)

<!--
TODO -/ Start using [GitHub pages](https://pages.github.com/) in my main repo. It's time I started a blog of some sort.
-->