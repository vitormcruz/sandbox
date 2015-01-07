package sandbox.magritte.testGenerator


interface TestScenario {

    String getTestName()
    Closure getClojure()

}