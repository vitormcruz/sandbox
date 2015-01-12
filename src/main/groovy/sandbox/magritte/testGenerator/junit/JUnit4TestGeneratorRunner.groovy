package sandbox.magritte.testGenerator.junit
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.InitializationError
import sandbox.magritte.methodGeneration.generator.imp.MethodTeacher
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitTestGeneratorForTestDescription

class JUnit4TestGeneratorRunner extends BlockJUnit4ClassRunner{

    //TODO somehow make it use other runner instead of extend. That way, this runner can be used with any other.
    /**
     * Constructs a new instance of the default runner
     */
    JUnit4TestGeneratorRunner(Class<?> klass) throws InitializationError {
        super(klass)
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        def knownMethods = new ArrayList<>(super.computeTestMethods())
        def teacher = new MethodTeacher(JUnitTestGeneratorForTestDescription)
        def teachedMethods = teacher.teach(getTestClass().getJavaClass())
        teachedMethods.each {knownMethods.add(new FrameworkMetaMethod(it))}
        return knownMethods
    }
}
