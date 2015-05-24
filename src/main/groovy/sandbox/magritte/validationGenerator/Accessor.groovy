package sandbox.magritte.validationGenerator

/**
 */
class Accessor {
    def String name
    def private delegate

    def getValue(){
        def clojure = {return delegate."${name}"}
        clojure.delegate = delegate
        return clojure()
    }

    void setDelegate(delegate) {
        this.delegate = delegate
    }
}
