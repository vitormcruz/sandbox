package sandbox.magritte.testGenerator

import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import sandbox.smartfactory.SmartFactory
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator

import static groovy.test.GroovyAssert.shouldFail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class TestGeneratorForTestDescriptionTest {
    private MandatoryTestGenerator mandatoryTestGeneratorMock

    @Before
    def void setup(){
        mandatoryTestGeneratorMock = mock(MandatoryTestGenerator)
        SmartFactory.configureForTest().configurationFor(TestGeneratorForTestDescription).put(MandatoryTestGenerator, mandatoryTestGeneratorMock)
    }

    @After
    def void cleanup(){
        SmartFactory.resetConfigForTest()
    }

    @Test
    def void "Create a test description for null class"(){
        def ex = shouldFail(IllegalArgumentException,
                            { new TestGeneratorForTestDescription().descriptionsFor(null, [] as Description[]) })
        assert ex.message == "Cannot create test scenarios for a TestDescription that do not specify a target class"
    }

    @Test
    def void "Create a test description without specify any definition for the class under test"(){
        def testDescription = new TestGeneratorForTestDescription().descriptionsFor(ClassUnderTest, [] as Description[])
        assert testDescription.getGeneratedMethods().isEmpty() : "No definition test suit description should not generate any test methods."
    }

    @Test
    def void "Create a test description specifying no definition but mandatory test generator generating N methods"(){
        def generatedMethodMock = mock(GeneratedMethod)
        def generatedMethodMock2 = mock(GeneratedMethod)
        when(mandatoryTestGeneratorMock.getGeneratedMethods()).thenReturn([generatedMethodMock, generatedMethodMock2])

        def descriptions = [createMockDescriptionReturning([])] as Description[]
        assert [generatedMethodMock, generatedMethodMock2] as Set == generatedMethodsFor(descriptions) as Set
    }

    @Test
    def void "Create a test description specifying one definition with no generated methods"(){
        def descriptions = [createMockDescriptionReturning([])] as Description[]
        assert [] == generatedMethodsFor(descriptions)
    }

    @Test
    def void "Create a test description specifying one definition returning one generated method"(){
        def generatedMethodMock = mock(GeneratedMethod)
        def mockDescription = createMockDescriptionReturning([generatedMethodMock])
        def descriptions = [mockDescription] as Description[]
        assert [generatedMethodMock] == generatedMethodsFor(descriptions)
    }

    @Test
    def void "Create a test description specifying one definition returning N generated methods"(){
        def generatedMethodMock = mock(GeneratedMethod)
        def generatedMethodMock2 = mock(GeneratedMethod)
        def mockDescription = createMockDescriptionReturning([generatedMethodMock, generatedMethodMock2])
        def descriptions = [mockDescription] as Description[]
        assert [generatedMethodMock, generatedMethodMock2] as Set == generatedMethodsFor(descriptions) as Set
    }

    @Test
    def void "Create a test description specifying N definition and a mandatory test generator, each returning N generated methods."(){
        def generatedMethodMock = mock(GeneratedMethod)
        def generatedMethodMock2 = mock(GeneratedMethod)
        def generatedMethodMock3 = mock(GeneratedMethod)
        def generatedMethodMock4 = mock(GeneratedMethod)
        def generatedMethodMock5 = mock(GeneratedMethod)
        def generatedMethodMock6 = mock(GeneratedMethod)
        def mockDescription = createMockDescriptionReturning([generatedMethodMock, generatedMethodMock2])
        def mockDescription2 = createMockDescriptionReturning([generatedMethodMock3, generatedMethodMock4])
        when(mandatoryTestGeneratorMock.getGeneratedMethods()).thenReturn([generatedMethodMock5, generatedMethodMock6])
        def descriptions = [mockDescription, mockDescription2] as Description[]
        assert [generatedMethodMock, generatedMethodMock2, generatedMethodMock3, generatedMethodMock4,
                generatedMethodMock5, generatedMethodMock6] as Set == generatedMethodsFor(descriptions) as Set
    }


    private Collection<GeneratedMethod> generatedMethodsFor(Description[] descriptions) {
        return new TestGeneratorForTestDescription().descriptionsFor(ClassUnderTest, descriptions)
                                                    .getGeneratedMethods()
    }

    @Test
    @Ignore
    def void "Create a test description without specifying a validation clojure"(){}

    @Test
    @Ignore
    def void "Create a test description without specifying an object creation clojure"(){}

    @Test
    @Ignore
    def void "Create a test description specifying an object creation clojure"(){}

    def createMockDescriptionReturning(generatedMethods){
        def methodGeneratorMock = mock(MethodGenerator)
        when(methodGeneratorMock.getGeneratedMethods()).thenReturn(generatedMethods)
        def descriptionMock = new DescriptionMock()
        descriptionMock.mockMethodGenerator = methodGeneratorMock
        return descriptionMock
    }

    public static class ClassUnderTest{
    }

    /**
     *  Mock class created because there is no way to make mockito or groovy proxy mock properly the asTestGenerator
     *  that is added as an extension method. I guess it is too much magic to handle at the same time.... :(
     **/
    public static class DescriptionMock implements Description{

        def mockMethodGenerator

        public MethodGenerator asTestGenerator(descriptedClass, mandatoryTestGenerator){
            return mockMethodGenerator
        }
    }

}
