package testGenerator
import sandbox.magritte.DescriptionMethod
/**
 * Load a class with a test case based on a magritte description
 */
class TestCaseClassLoader {
    def load(Class aClass) {
        def testMethods = []

        def descriptionMethods = aClass.getMethods().findAll {it.declaredAnnotations.contains(DescriptionMethod)}

        assert descriptionMethods.size() <= 1 : /Sorry, only one description method for now. just for now..../

        if(descriptionMethods.size().equals(1)){
            def description = descriptionMethods[0]()

            assert description instanceof TestSuitDescription : /Sorry, messy here, only TestCaseDescriptions supported today... /

//            description.each {}
        }


        aClass.metaClass.static.getMagritteTests = {testMethods}
    }
}
