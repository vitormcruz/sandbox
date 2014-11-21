package testGenerator
import magritte.AbstractVisitableDescription
import magritte.Description

class TestSuitDescription extends AbstractVisitableDescription{
    Class classUnderTest
    def descriptions = []

    TestSuitDescription() {
    }

    TestSuitDescription(Class classUnderTest) {
        this.classUnderTest = classUnderTest
    }

    static TestSuitDescription forClass(Class classUnderTest) {
        if (classUnderTest == null) throw new IllegalArgumentException("Cannot create a test suit description for null class")
        return new TestSuitDescription(classUnderTest)
    }

    AbstractVisitableDescription addClassDefinition(AbstractVisitableDescription description) {
        descriptions.add(description)
        return this
    }

    //TODO think what to do with that.....
    @Override
    Description acessor(String acessor) {
        return null
    }

    @Override
    Description beRequired() {
        return null
    }

    @Override
    Description defaultValue(Object defaultValue) {
        return null
    }
    Collection getTestScenarios() {
        return []
    }
}
