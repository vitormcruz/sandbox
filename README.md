Sandbox
=======

##What do this project currently has:

1. Maven
2. Spring Boot with spring-boot-starter-web (using spring parent)
3. Vaadin + Spring MVC
    * Vaadin UI is mapped to /sandbox/*
    * Spring "/" redirects to sandbox/
    * Spring responds to /hello.do with a hello world message :)

4. Validation based on JUnit
5. Magritte for JVM
6. Vaadin UI generaction based on Magritte
7. Test generation based on Magritte

###Maven

I will eventually create a build based on Graddle while mantaining maven working so that I can compare them. 

###Spring Boot 

No XML please.

###Vaadin + Spring MVC

Experimenting Vaadin and exploring how could I use it alongside Spring MVC. 

###Validation based on JUnit

There are lots of validation frameworks with different mechanics. I have seen frameworks that:

1. Relies on annotations, which tent to be rigidy toward validation using the annotations on single properties only. Also, I don't see annotations as a good way to make validations, since validation is not a meta information of a property. I also like to stick to the common OO facilities, using classes and messages to create behavior leverages all the advantagens of the language and facilitates reuse and flexibility;
2. Uses callbacks in a very flexible, but complicated way; 
3. Uses XML. XML are less a problem because of its verbose nature than for its rigidy; 

I don't find good any of the approaches I looked at, but I couldn't think of a better solution also. Them I read the great book A Mentoring Course in Smalltalk (http://www.lulu.com/shop/andres-valloud/a-mentoring-course-on-smalltalk/paperback/product-3788890.html), which describes a very interesting way of doing validation and an easy way to implement it. The autor compare Testing to Validation, and realize that they are the same, since Testing is just a specific kind of Validation. The family of xUnit started in Smalltalk, so they have a SUnit, and since Testing is validation, the author proposes changing SUnit and create a validation framework that has, for free, all of SUnit features.

What I did here was the same, but with JUnit.

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
