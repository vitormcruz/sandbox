package sandbox.magritte.testGenerator.junit.scenarioGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.description.recordingDescription.InterfaceRecorder
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.testGenerator.MandatoryTestGeneratorForMethod
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.ValidationObserver

class JUnitTestsGeneratorForOperationDescription implements MethodGenerator, OperationDescription {

    private Object describedClass
    private String methodName
    private Collection<GeneratedMethod> generatedTests = []
    MandatoryTestGeneratorForMethod mandatoryTestGenerator = MandatoryTestGeneratorForMethod.smartNewFor(JUnitTestsGeneratorForOperationDescription);
    def validation

    JUnitTestsGeneratorForOperationDescription(def describedClass) {
        this.describedClass = describedClass
        mandatoryTestGenerator.setClassUnderTest(describedClass)
    }

    @Override
    OperationDescription named(String methodName) {
        this.methodName = methodName
        mandatoryTestGenerator.setMethodUnderTest(methodName)
        this.validation = getValidationMethodFor(methodName)
        mandatoryTestGenerator.setValidationMethod(validation)
        return this
    }

    @Override
    OperationDescription withParameter(Object number, Object name, Description description) {
        Description newDescription = getDescriptionWithAccessor(description, name)
        def testGenerator = newDescription.asTestGenerator(describedClass, mandatoryTestGenerator)
        testGenerator.setValidationMethod(validation)


        generatedTests.addAll(testGenerator.getGeneratedMethods())
        return this
    }

    //TODO MUST change this. Implementation of models cannot depend upon the order of which descriptions are made.
    private Description getDescriptionWithAccessor(Description description, name) {
        Description newDescription = new InterfaceRecorder(description.getInterfaceBeenRecorded()).asTypeBeingRecorded()
        newDescription.accessor(name)
        description.playbackAt(newDescription)
        newDescription
    }

    private Closure<List<String>> getValidationMethodFor(name) {
        { params ->
            def testSubject = "newInstance".equals(name) ? describedClass : describedClass.newInstance()
            def validationObserver = new SimpleValidationObserver()
            ApplicationValidationNotifier.addObserver(validationObserver)
            testSubject."${name}"(*params)
            return validationObserver.getErrors()
        }
    }

    @Override
    OperationDescription forConstructor() {
        named("newInstance")
        return this
    }

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return [generatedTests, mandatoryTestGenerator.getGeneratedMethods()].flatten()
    }

    public static class SimpleValidationObserver implements ValidationObserver{

        private errors = []

        @Override
        void startValidation(String validationName) {

        }

        @Override
        void issueError(String error) {
            errors.add(error)
        }

        def getErrors() {
            return new ArrayList(errors)
        }

        @Override
        void finishValidation(String validationName) {

        }

        @Override
        Boolean successful() {
            return errors.isEmpty()
        }
    }
}
