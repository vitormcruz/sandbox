package sandbox.magritte.methodGeneration.imp

import sandbox.magritte.methodGeneration.GeneratedMethod


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
