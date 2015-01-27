package sandbox.magritte.testGenerator.junit
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.InitializationError
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.imp.MethodTeacher

class JUnit4TestGeneratorRunner extends BlockJUnit4ClassRunner{

    def static private methodTeacher = new MethodTeacher()

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
        def teachMethods = methodTeacher.teach(getTestClass().getJavaClass(), getGeneratedMethods())
        teachMethods.each {knownMethods.add(new FrameworkMetaMethod(it))}
        return knownMethods
    }

    private Collection<GeneratedMethod> getGeneratedMethods() {
        return MagritteDescriptionModelBuilder.forObject(getTestClass().getJavaClass().newInstance()).asTestGenerator()
                                                                                                     .getGeneratedMethods()
    }
}
