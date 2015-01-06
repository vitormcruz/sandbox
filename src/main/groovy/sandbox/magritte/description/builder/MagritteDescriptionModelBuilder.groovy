package sandbox.magritte.description.builder

import sandbox.magritte.description.DescriptionContainer
import sandbox.magritte.description.DescriptionModelDefinition

import java.lang.reflect.Method

import static sandbox.magritte.description.builder.DescriptionFactory.New

/**
 */
class MagritteDescriptionModelBuilder {

    def static forObject(object){
        Collection<Method> descriptionMethods = object.getClass()
                                                      .methods.findAll {method ->
            method.declaredAnnotations.find {it instanceof DescriptionModelDefinition} != null
        }


        if (descriptionMethods[0] == null) {
            return New(DescriptionContainer)
        }

        def descriptionModel = descriptionMethods[0].invoke(object)
        return descriptionModel == null ? New(DescriptionContainer) : descriptionModel
    }
}
