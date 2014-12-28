package sandbox.validator

import java.lang.annotation.*

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface Validation {

}