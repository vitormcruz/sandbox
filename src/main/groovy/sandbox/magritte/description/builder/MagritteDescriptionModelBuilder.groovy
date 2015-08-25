package sandbox.magritte.description.builder

import sandbox.magritte.description.DescriptionModelDefinition

import java.lang.reflect.Method

/**
 */
class MagritteDescriptionModelBuilder {

    def static forObjectA(object){
        Collection<Method> descriptionMethods = object.getClass()
                                                      .methods.findAll {method ->
            method.declaredAnnotations.find {it instanceof DescriptionModelDefinition} != null
        }


        if (descriptionMethods[0] == null) {
            return []
        }

        def descriptionModel = descriptionMethods[0].invoke(object)
        return descriptionModel == null ? [] : descriptionModel
    }

    def forObject(object){
        Collection<Method> descriptionMethods = object.getClass()
                .methods.findAll {method ->
            method.declaredAnnotations.find {it instanceof DescriptionModelDefinition} != null
        }


        if (descriptionMethods[0] == null) {
            return []
        }

        def descriptionModel = descriptionMethods[0].invoke(object)
        return descriptionModel == null ? [] : descriptionModel
    }

}

