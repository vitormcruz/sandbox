package magritte.test

import magritte.Description
import magritte.DescriptionVisitor


class TestSuitDescription extends Description{

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

    def addRestrictionDefinition(Description description) {
        descriptions.add(description)
    }

    def visit(DescriptionVisitor descritionVisitor){
        descritionVisitor.descriptionOf(classUnderTest)

    }

}
