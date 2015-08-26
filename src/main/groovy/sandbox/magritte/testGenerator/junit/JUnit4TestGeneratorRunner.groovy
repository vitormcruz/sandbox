package sandbox.magritte.testGenerator.junit

import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.InitializationError
import org.junit.runners.model.TestClass

class JUnit4TestGeneratorRunner extends BlockJUnit4ClassRunner{

    private TestGenerator testGenerator

    /**
     * Constructs a new instance of the default runner
     */
    JUnit4TestGeneratorRunner(Class<?> klass) throws InitializationError {
        super(klass)
    }

    @Override
    protected TestClass createTestClass(Class<?> testClass) {
        /**
         * testGenerator need to be initialized so that computeTestMethods can be used, but computeTestMethods is used
         * in the constructor of the ParentRunner, and since the initialization occurs after the construction
         * inheritance chain, computeTestMethods get called before the initialization. Since createTestClass is called
         * by the ParentRunner constructor before anything else, this is the best place to put the testGenerator
         * initialization and avoid the problem.
         */
        testGenerator = TestGenerator.smartNewFor(JUnit4TestGeneratorRunner)
        return super.createTestClass(testClass)
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        def knownMethods = new ArrayList<>(super.computeTestMethods())
        def testObject = getTestClass().getJavaClass().newInstance()
        testGenerator.getGeneratedMethodsFor(testObject).each {
            it.teachMyselfTo(testObject)
            knownMethods.add(new FrameworkMetaMethod(testObject.class.metaClass.getMetaMethod(it.methodName)))
        }
        return knownMethods
    }

}
