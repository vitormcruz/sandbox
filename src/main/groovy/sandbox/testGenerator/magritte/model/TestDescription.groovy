package sandbox.testGenerator.magritte.model
import sandbox.magritte.Description

interface TestDescription {

    def TestDescription descriptionsFor(Class forClass, Description... descriptions);

}
