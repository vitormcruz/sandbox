package sandbox.magritte.testGenerator.junit.scenarioGenerator

import sandbox.validator.imp.ValidationException

class ValidationFactory {

    public Closure<List<String>> getValidationMethodFor(name, describedClass) {
        { params ->
            def testSubject = "newInstance".equals(name) ? describedClass : describedClass.newInstance()

            try {
                testSubject."${name}"(params)
                testSubject.validateFailingOnError()
                return []
            } catch (ValidationException e) {
                return e.result.getFailures().collect { it.getException().getMessage() }
            }
        }
    }
}
