package sandbox.magritte.description

import java.lang.annotation.*

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface DescriptionModelDefinition {

}