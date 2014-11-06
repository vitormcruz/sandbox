package magritte


class TestSuitDescription extends Description{

    Class classUnderTest
    def descriptions = [:]

    TestSuitDescription() {
    }

    TestSuitDescription(Class classUnderTest) {
        this.classUnderTest = classUnderTest
    }

    TestSuitDescription forClass(Class classUnderTest) {
        return new TestSuitDescription(classUnderTest)
    }

    def defineFieldAs(String field, Description description) {
        descriptions.put(field, description)
    }
}
