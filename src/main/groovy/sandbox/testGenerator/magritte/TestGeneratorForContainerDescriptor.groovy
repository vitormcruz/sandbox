package sandbox.testGenerator.magritte

import sandbox.magritte.Description
import sandbox.magritte.IDescriptionContainer
import sandbox.testGenerator.TestScenario


class TestGeneratorForContainerDescriptor implements IDescriptionContainer{
    Collection<TestScenario> tests = new ArrayList<TestScenario>()
    private Class descriptedClass

    TestGeneratorForContainerDescriptor(Class descriptedClass) {
        this.descriptedClass = descriptedClass
    }

    @Override
    IDescriptionContainer addAll(Collection<? extends Description> descriptions) {
        descriptions.each {
            tests.addAll(it.asTestScenariosFor(descriptedClass))
        }

        return this
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

    public Collection<TestScenario> getTestScenarios(){
        return tests
    }
}
