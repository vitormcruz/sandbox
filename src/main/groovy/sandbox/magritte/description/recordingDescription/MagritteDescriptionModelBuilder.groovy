package sandbox.magritte.description.recordingDescription

import sandbox.magritte.description.DescriptionContainer
import sandbox.magritte.description.DescriptionMethod

import java.lang.reflect.Method

import static sandbox.magritte.description.DescriptionFactory.New
/**
 */
class MagritteDescriptionModelBuilder {

    def static forObject(object){
        Collection<Method> descriptionMethods = object.getClass()
                                                      .methods.findAll {method ->
            method.declaredAnnotations.find {it instanceof DescriptionMethod} != null
        }

        //TODO make it consider many methods annotated with DescriptionMethod
        if(descriptionMethods[0] == null){
            return New(DescriptionContainer)
        }
        return descriptionMethods[0].invoke(object)
    }
}
