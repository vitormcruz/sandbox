package sandbox.magritte.validationGenerator
/**
 *
 * @param < T > GeneratedMethod class
 */
interface ValidationFactory<T>{

    def T getMaxSizeValidation(accessor, maxSize)
    def T getRequiredValidation(accessor)
}