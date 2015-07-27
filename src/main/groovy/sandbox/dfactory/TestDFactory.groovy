package sandbox.dfactory

/**
 */
class TestDFactory extends DFactory{
    private DFactory originalDfactory

    TestDFactory(DFactory dFactory) {
        this.originalDfactory = dFactory
        this.putAll(dFactory)
    }

    @Override
    DFactory getOriginalConfiguration() {
        return originalDfactory
    }
}
