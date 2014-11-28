package sandbox.testGenerator.magritte

import sandbox.magritte.Description
import sandbox.magritte.IDescriptionContainer
import sandbox.testGenerator.TestScenario


class TestGeneratorDescriptionContainer implements IDescriptionContainer {

    Collection<TestScenario> testScenarios = []
    private Class forClazz

    //TODO this should be visitable
    TestGeneratorDescriptionContainer(Class forClazz) {
        this.forClazz = forClazz
    }

    @Override
    IDescriptionContainer addAll(Collection<? extends Description> descriptions) {
        descriptions.each {
            testScenarios.addAll(it.asTestScenariosFor(forClazz))
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
}
