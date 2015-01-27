package sandbox.magritte.testGenerator.junit
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.InitializationError
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.methodGenerator.imp.MethodTeacher
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
        //TODO extract dynamic method creation into construction so that MethodTeacher do not teach every time
        def knownMethods = new ArrayList<>(super.computeTestMethods())
        def teacher = new MethodTeacher()


        def descriptionModel = MagritteDescriptionModelBuilder.forObject(getTestClass().getJavaClass().newInstance())
        def methodGenerator = JUnitTestGeneratorForTestDescription.newInstance()
        descriptionModel.accept(methodGenerator)


        def teachedMethods = teacher.teach(getTestClass().getJavaClass(), methodGenerator.getGeneratedMethods())
        teachedMethods.each {knownMethods.add(new FrameworkMetaMethod(it))}
        return knownMethods
    }
}
