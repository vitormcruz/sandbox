package sandbox.magritte.validationGenerator

import sandbox.magritte.methodGenerator.GeneratedMethod

/**
 *
 */
interface ValidationFactory{

    def GeneratedMethod getMaxSizeValidation(Accessor accessor, maxSize)
    def GeneratedMethod getRequiredValidation(Accessor accessor)
    def GeneratedMethod getBeNaturalValidation(Accessor accessor)
    def GeneratedMethod getBeNotBlankValidation(Accessor accessor)
}