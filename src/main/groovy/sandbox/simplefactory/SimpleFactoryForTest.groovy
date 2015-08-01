package sandbox.simplefactory

/**
 */
class SimpleFactoryForTest extends SimpleFactory{
    private SimpleFactory originalSimpleFactory

    SimpleFactoryForTest(SimpleFactory simpleFactory) {
        this.originalSimpleFactory = simpleFactory
        this.putAll(simpleFactory)
    }

    @Override
    SimpleFactory getOriginalConfiguration() {
        return originalSimpleFactory
    }
}
