package sandbox.magritte.description

interface TestDescription {

    def TestDescription descriptionsFor(Class forClass, Description... descriptions);
    def TestDescription usingThisValidationMethod(Closure validationMethod);

}
