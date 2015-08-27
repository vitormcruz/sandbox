package sandbox.magritte.description.builder

import sandbox.magritte.description.DescriptionModelDefinition

import java.lang.reflect.Method
//TODO create interface and make smart new consider it.
/**
 */
class MagritteDescriptionModelBuilder {

    def final static MagritteDescriptionModelBuilder myInstance = MagritteDescriptionModelBuilder.smartNewFor(MagritteDescriptionModelBuilder)

    def forObject(object){
        Collection<Method> descriptionMethods = object.getClass().methods.findAll {method ->
            method.declaredAnnotations.find {it instanceof DescriptionModelDefinition} != null
        }

        def descriptionModel = descriptionMethods.collect{ it.invoke(object) }.flatten()
        descriptionModel.removeAll { it == null }
        return descriptionModel
    }

}

