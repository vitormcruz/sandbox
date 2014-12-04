package sandbox.testGenerator


class TestScenario {
    def final testName
    def final clojure

    TestScenario() {
    }

    TestScenario(testName, clojure) {
        this.testName = testName
        this.clojure = clojure
    }
}
