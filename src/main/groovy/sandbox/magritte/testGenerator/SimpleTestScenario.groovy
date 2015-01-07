package sandbox.magritte.testGenerator


class SimpleTestScenario implements TestScenario{
    def final String testName
    def final Closure clojure

    SimpleTestScenario() {
    }

    SimpleTestScenario(String testName, Closure clojure) {
        this.testName = testName
        this.clojure = clojure
    }
}
