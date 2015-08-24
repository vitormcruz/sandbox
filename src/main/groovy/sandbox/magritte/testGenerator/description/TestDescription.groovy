package sandbox.magritte.testGenerator.description

import sandbox.magritte.description.Description

interface TestDescription {

    def TestDescription descriptionsFor(Class forClass, Description... descriptions);
    def TestDescription usingThisValidationMethod(Closure validationMethod);

}
