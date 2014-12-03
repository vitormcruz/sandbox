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

        //TODO make it consider many methods annotated with DescriptionMethod
        if(descriptionMethods[0] == null){
            return new DescriptionContainer(object.getClass())
        }
        return descriptionMethods[0].invoke(object) as Description
    }
}
