package sandbox.magritte.testGenerator.description
import sandbox.magritte.description.Description

interface TestDescription extends Description{

    def TestDescription descriptionsFor(Class forClass, Description... descriptions);

}
