package sandbox.magritte.methodGenerator.imp

import sandbox.magritte.methodGenerator.GeneratedMethod


class SimpleGeneratedMethod implements GeneratedMethod{
    def final String methodName
    def final Closure clojure

    SimpleGeneratedMethod() {
    }

    SimpleGeneratedMethod(String methodName, Closure clojure) {
        this.methodName = methodName
        this.clojure = clojure
    }
}
