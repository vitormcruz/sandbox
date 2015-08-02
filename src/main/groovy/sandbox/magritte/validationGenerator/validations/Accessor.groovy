package sandbox.magritte.validationGenerator.validations

/**
 */
class Accessor {
    def String name
    protected delegate

    def getValue(){
        def clojure = getClojureValue()
        clojure.delegate = delegate
        return clojure()
    }

    def getClojureValue() {
        return { return delegate."${name}" }
    }

    void setDelegate(delegate) {
        this.delegate = delegate
    }
}
