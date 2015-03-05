package sandbox.magritte.methodGenerator.imp

import sandbox.magritte.methodGenerator.GeneratedMethod


class SimpleGeneratedMethod implements GeneratedMethod{
    protected String methodName
    protected Closure clojure

    SimpleGeneratedMethod() {
    }

    SimpleGeneratedMethod(String methodName, Closure clojure) {
        this.methodName = methodName
        this.clojure = clojure
    }

    @Override
    String getMethodName() {
        return methodName
    }

    @Override
    Closure getClojure() {
        return clojure
    }
}
