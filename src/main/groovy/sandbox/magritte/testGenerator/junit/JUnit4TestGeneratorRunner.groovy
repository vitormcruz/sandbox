package sandbox.magritte.testGenerator.junit
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.InitializationError
import sandbox.magritte.testGenerator.TestTeacher

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
        def methods = new ArrayList<>(super.computeTestMethods())
        def metaMethods = new TestTeacher().teach(getTestClass().getJavaClass())
        metaMethods.each {methods.add(new FrameworkMetaMethod(it))}
        return methods
    }
}
