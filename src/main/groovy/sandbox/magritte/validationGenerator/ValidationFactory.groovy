package sandbox.magritte.validationGenerator

import sandbox.magritte.methodGenerator.GeneratedMethod

/**
 *
 */
interface ValidationFactory{

    def GeneratedMethod getMaxSizeValidation(accessor, maxSize)
    def GeneratedMethod getRequiredValidation(accessor)
    def GeneratedMethod getBeNaturalValidation(accessor)
    def GeneratedMethod getBeNotBlankValidation(accessor)
}