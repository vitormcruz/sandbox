Sandbox
=======

![Build Status](https://travis-ci.org/vitormcruz/sandbox.svg?branch=master)
                

Project in which I experiment various technologies and techniques.

##What do this project currently has:

####Maven

I will eventually create a build based on Graddle while maintaining maven working so that I can compare them.

####~~Spring Boot with spring-boot-starter-web (using spring parent)~~

<fill>

####~~Vaadin + Spring MVC~~

_Using [Spark](http://sparkjava.com) now. See _

####~~Validation using an uniform notification pattern~~

_Moved to_
 
####~~Implementation of Payroll~~

_Moved to_

####~~Validation based on JUnit~~

_Gave up to a different approach, see Validation using an uniform notification pattern section._

####~~Magritte for JVM~~

_Very promising, but I gave up for lack of time. I maintained, however, a branch with the partial implementation_

[Magritte](https://github.com/magritte-metamodel/magritte) is a meta-description framework that keeps full power to the 
programmer since the description is made with pure OO. A description alone is useless, but it can be used to any 
purpose, most notably to generate dynamic stuff, since the description of objects can be obtained anytime at runtime. 
In Smalltalk it is commonly used to generate web forms in the [Seaside Framework](http://book.seaside.st/book/advanced/magritte). 
The JVM implementation is more or less like the one in Smalltalk, and I aim to use it to generate not only web forms, 
but validation and tests also.

For example suppose a class Employee with a name and age, a description of this class could be:

```
   @DescriptionDefinition
   def myDescritpion(){
     ["name".isAString().beRequired(),
     "age".isAnInteger().minSize(18).beRequired()]
   }
```

With this description I can generate, dynamically:
* A web form that validates the required fields and that the age is an Integer => 18;
* Validations on the server side;
* An object mother builder class that builds valid Employee classes;
* An entire web API;
* The sky is the limit....

I intent to do the following:

1. ~~Vaadin UI generation based on Magritte~~ 
2. ~~Test generation based on Magritte~~
3. ~~Validation generation based on Magritte~~