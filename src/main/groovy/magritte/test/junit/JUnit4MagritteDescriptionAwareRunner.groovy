package magritte.test.junit
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.InitializationError

class JUnit4MagritteDescriptionAwareRunner extends BlockJUnit4ClassRunner{

    /**
     * Constructs a new instance of the default runner
     */
    JUnit4MagritteDescriptionAwareRunner(Class<?> klass) throws InitializationError {
        super(klass)
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        def methods = new ArrayList<>(super.computeTestMethods())
        def testClass = getTestClass().getJavaClass()
        testClass.metaClass.testeDinamico = {assert false: "Funcionou!!!!!!!!!!!!!"}
        def testeBliBli = testClass.metaClass.methods.find { it.name.equals("testeDinamico") }
        methods.add(new FrameworkMetaMethod(testeBliBli))
        return methods
    }
}
