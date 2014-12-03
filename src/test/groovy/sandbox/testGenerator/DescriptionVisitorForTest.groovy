package sandbox.testGenerator

import sandbox.magritte.Description


class DescriptionVisitorForTest implements Description{

    static def testScenarios = [new TestScenario()]

    DescriptionVisitorForTest(Class forClass) {
    }

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

    @Override
    Description label(Object label) {
        return null
    }

    def getTestScenarios(){
        return testScenarios
    }
}
