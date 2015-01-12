package sandbox.magritte.testGenerator.description

import sandbox.magritte.description.ConceptDescription
import sandbox.magritte.description.Description

interface TestDescription extends ConceptDescription{

    def TestDescription descriptionsFor(Class forClass, Description... descriptions);

}
