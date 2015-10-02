package sandbox.magritte.testGenerator.junit.scenarioGenerator

import sandbox.magritte.description.ObjectDescription
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.ObjectDescription
import sandbox.magritte.description.StringDescription

class JUnitTestsGeneratorForNumberDescription extends JunitTestGeneratorForObjectDescription implements NumberDescription{

    private Object describedClass

    JUnitTestsGeneratorForNumberDescription(describedClass) {
        this.describedClass = describedClass
    }

    @Override
    NumberDescription beNatural() {
        return null
    }

    @Override
    ObjectDescription defaultValue(Object defaultValue) {
        return this
    }

    @Override
    ObjectDescription label(Object label) {
        return this
    }
}