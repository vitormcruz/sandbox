package sandbox.magritte.testGenerator.junit.scenarioGenerator

import sandbox.magritte.description.Description
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.description.recordingDescription.MessageRecorder
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.testGenerator.MandatoryTestGeneratorForMethod
import sandbox.validator.imp.ValidationException

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
    OperationDescription withParameter(Object number, Description description) {
        Description newDescription = getDescriptionWithAccessor(description)
        def testGenerator = newDescription.asTestGenerator(describedClass, mandatoryTestGenerator)
        testGenerator.setValidationMethod(validation)


        generatedTests.addAll(testGenerator.getGeneratedMethods())
        return this
    }

    //TODO MUST change this. Implementation of models cannot depend upon the order of which descriptions are made.
    private Description getDescriptionWithAccessor(Description description) {
        Description newDescription = new MessageRecorder(description.getInterfaceBeenRecorded()).asTypeBeingRecorded()
        def recorder = new MessageRecorder(description.getInterfaceBeenRecorded())
        description.playbackAt(recorder)
        def accessor = recorder.recordedMethods.find {it.name == "accessor"}.args[0]

        newDescription.accessor(accessor)
        description.playbackAt(newDescription)
        newDescription
    }

    private Closure<List<String>> getValidationMethodFor(name) {
        { params ->
            def testSubject = "newInstance".equals(name) ? describedClass : describedClass.newInstance()

            try {
                testSubject."${name}"(*params)
                return []
            } catch (ValidationException e) {
                return e.result.getFailures().collect { it.getException().getMessage() }
            }
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
}
