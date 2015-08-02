package sandbox.smartfactory

/**
 */
class SmartFactoryForTest extends SmartFactory{
    private SmartFactory originalSmartFactory

    SmartFactoryForTest(SmartFactory smartFactory) {
        this.originalSmartFactory = smartFactory
        this.putAll(smartFactory)
    }

    @Override
    SmartFactory getOriginalConfiguration() {
        return originalSmartFactory
    }
}
