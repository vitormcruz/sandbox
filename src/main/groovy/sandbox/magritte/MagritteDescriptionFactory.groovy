package sandbox.magritte

import java.lang.reflect.Method

/**
 */
class MagritteDescriptionFactory {

    public static Description forObject(object){
        Collection<Method> descriptionMethods = object.getClass()
                                                      .methods.findAll {method ->
            method.declaredAnnotations.find {it instanceof DescriptionMethod} != null
        }

        return descriptionMethods[0].invoke(object) as Description
    }
}
