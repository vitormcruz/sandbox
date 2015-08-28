package sandbox.magritte.description.builder

import sandbox.magritte.description.DescriptionModelDefinition

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
//TODO create interface and make smart new consider it.
/**
 */
class MagritteDescriptionModelBuilder {

    def final static MagritteDescriptionModelBuilder myInstance = MagritteDescriptionModelBuilder.smartNewFor(MagritteDescriptionModelBuilder)

    def forObject(describedObject){
        Collection<Method> descriptionMethods = describedObject.getClass().methods.findAll {method ->
            method.declaredAnnotations.find {it instanceof DescriptionModelDefinition} != null
        }

        def descriptionModel = descriptionMethods.collect{ descriptionMethod -> execute(describedObject, descriptionMethod) }.flatten()
        descriptionModel.removeAll { it == null }
        return descriptionModel
    }

    def execute(describedObject, descriptionMethod){
        try {
            return descriptionMethod.invoke(describedObject)
        } catch (InvocationTargetException e) {
            throw e.targetException
        }
    }

}

