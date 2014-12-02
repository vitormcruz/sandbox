package sandbox.testGenerator
import sandbox.magritte.DescriptionMethod
import sandbox.magritte.StringDescription
import org.junit.Ignore
import org.junit.Test

class TestCaseClassLoaderTest {

    TestCaseClassLoader testCaseLoader = new TestCaseClassLoader()

    @Test
    def void "Load class without description"(){
        testCaseLoader.load(ClassWithoutDescription)
        assert ClassWithoutDescription.magritteTests.isEmpty()
    }

    public static class ClassWithoutDescription {}

    @Test
    def void "Load class with an empty description"(){
        testCaseLoader.load(ClassWithEmptyDescription)
        assert ClassWithEmptyDescription.magritteTests.isEmpty()
    }

    public static class ClassWithEmptyDescription {
        @DescriptionMethod
        def empty(){
        }
    }

    @Ignore("incomplete due to TestSuitDescriptionTest implementation")
    def void "Load class with one Test Suit description"(){
        testCaseLoader.load(ClassWithATestSuitDescription)
        assert ClassWithATestSuitDescription.magritteTests.size == 2
    }

    public static class ClassWithATestSuitDescription {
        @DescriptionMethod
        def myDescription(){
//            def testSuitDescription = new TestSuitDescription().forClass(ClassUnderTest)
            testSuitDescription.addRestrictionDefinition("name", new StringDescription().beRequired())
            return testSuitDescription
        }
    }

    //TODO Chamando duas vezes o load
    //TODO passar o load para dentro a classe object. Object.loadMagritteDerivedTestCases ou coisa parecida

    public static class ClassUnderTest{
        def name
    }
}
