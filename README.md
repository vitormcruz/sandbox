Sandbox
=======

##What do this project currently has:

1. Maven
2. Groovy for everything
3. Spring Boot with spring-boot-starter-web (using spring parent)
4. Vaadin + Spring MVC
    * Vaadin UI is mapped to /sandbox/*
    * Spring "/" redirects to sandbox/
    * Spring responds to /hello.do with a hello world message :)

5. Validation based on JUnit
6. Magritte for JVM
7. Vaadin UI generaction based on Magritte
8. Test generation based on Magritte


## General TODO

### Validation
2. Generalize error message build
3. Create a new failure that provides more information of the error, such as the aspect been tested
4. Optimize method generation to occur only one time
5. Consider hierarchy
6. Consider validation of an object that depends of the validation of other object (circular dependency)
7. Avoid running tests again if nothing changed
8. Parallel running???

### Test generator
1. Generalize Test name build
2. Optimize method generation
3. Optimize method generation to occur only one time
4. Consider hierarchy
