package sandbox.magritte.testGenerator.junit.scenarioGenerator

import sandbox.magritte.description.BaseDescription
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.StringDescription

class JUnitTestsGeneratorForNumberDescription extends JunitTestGeneratorForBaseDescription implements NumberDescription{

    private Object describedClass

    JUnitTestsGeneratorForNumberDescription(describedClass) {
        this.describedClass = describedClass
    }

    @Override
    NumberDescription beNatural() {
        return null
    }

    @Override
    BaseDescription defaultValue(Object defaultValue) {
        return this
    }

    @Override
    BaseDescription label(Object label) {
        return this
    }
}
