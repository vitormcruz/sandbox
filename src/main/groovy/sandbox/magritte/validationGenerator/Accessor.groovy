package sandbox.magritte.validationGenerator

/**
 */
class Accessor {
    def String name

    def getValue(delegate){
        def clojure = {return delegate."${name}"}
        clojure.delegate = delegate
        return clojure()
    }

}
