Sandbox
=======

![Build Status](https://travis-ci.org/vitormcruz/sandbox.svg?branch=master)
                

Project in which I experiment various technologies and techniques.

##What do this project currently has:

####Maven

I will eventually create a build based on Graddle while maintaining maven working so that I can compare them.

####Spring Boot with spring-boot-starter-web (using spring parent)

No XML, please.

####Vaadin + Spring MVC

Experimenting Vaadin and exploring how I could use it alongside Spring MVC.

* Vaadin UI is mapped to /com.vmc.sandbox/*
* Spring "/" redirects to com.vmc.sandbox/
* Spring responds to /hello.do with a hello world message :)

####Validation using an uniform notification pattern

There are lots of validation frameworks with different mechanics. I have seen frameworks that:

1. Relies on annotations, which tent to be rigid toward validation using the annotations on single properties only. 
Also, I don't see annotations as a good way to make validations, since validation is not a meta-information of a 
property. I also like to stick to the common OO facilities, using classes and messages to create behavior leverages 
all the advantages of the language and facilitates reuse and flexibility;
2. Uses callbacks in a very flexible but complicated way; 
3. Uses XML. XML are less a problem because of its verbose nature than for its rigidity;

I don't find good any of the approaches I looked at, but I couldn't think of a better solution also. Them I read the 
great book [A Mentoring Course in Smalltalk](http://www.lulu.com/shop/andres-valloud/a-mentoring-course-on-smalltalk/paperback/product-3788890.html), 
which describes a very interesting way of doing validation and an easy way to implement it. The author compare 
Testing to Validation, and realize that they are the same, since Testing is just a specific kind of Validation. 
The family of xUnit started in Smalltalk, so they have a SUnit, and since Testing is validation, the author proposes 
changing SUnit and create a validation framework that has, for free, all of SUnit features.

I did here was the same, but with JUnit, I like the results, but then I decided to use a purely 
[notification pattern](http://martinfowler.com/eaaDev/Notification.html), and I thinks it is better since I can cut the 
 dependency with JUnit, make things easier (JUnit provides notifications) and specialized. 

####Implementation of Payroll

TODO

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