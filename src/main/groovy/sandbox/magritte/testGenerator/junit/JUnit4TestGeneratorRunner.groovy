package sandbox.magritte.testGenerator.junit
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.InitializationError
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.methodGenerator.GeneratedMethod

class JUnit4TestGeneratorRunner extends BlockJUnit4ClassRunner{

    static private MagritteDescriptionModelBuilder modelBuilder = MagritteDescriptionModelBuilder.smartNewFor(JUnit4TestGeneratorRunner);

    /**
     * Constructs a new instance of the default runner
     */
    JUnit4TestGeneratorRunner(Class<?> klass) throws InitializationError {
        super(klass)
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        def knownMethods = new ArrayList<>(super.computeTestMethods())
        def testObject = getTestClass().getJavaClass().newInstance()
        getGeneratedMethodsFor(testObject).each {
            it.teachMyselfTo(testObject)
            knownMethods.add(new FrameworkMetaMethod(testObject.class.metaClass.getMetaMethod(it.methodName)))
        }
        return knownMethods
    }

    private Collection<GeneratedMethod> getGeneratedMethodsFor(testObject) {
        return modelBuilder.forObject(testObject).asTestGenerator().getGeneratedMethods()
    }
}
