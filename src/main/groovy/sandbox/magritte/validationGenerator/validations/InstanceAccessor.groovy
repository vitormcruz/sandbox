package sandbox.magritte.validationGenerator.validations

/**
 */
class InstanceAccessor implements Accessor{
    private String name
    def final describedObject

    InstanceAccessor(describedObject) {
        this.describedObject = describedObject
    }

    String getName() {
        return name
    }

    def getValue(){
        return describedObject."${name}"
    }
}
