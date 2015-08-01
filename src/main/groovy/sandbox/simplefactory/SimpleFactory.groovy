package sandbox.simplefactory

import sandbox.magritte.testGenerator.TestGeneratorForTestDescription

/**
 */
class SimpleFactory implements Map<Class, Configuration>{
    private static SimpleFactory dFactoryInstance = new SimpleFactory()

    @Delegate
    private Map<Class, Configuration> configurations = new Hashtable<Class, Configuration>()

    def static SimpleFactory instance(){
        return dFactoryInstance
    }

    static SimpleFactoryForTest configureForTest() {
        def dFactory = new SimpleFactoryForTest(dFactoryInstance)
        dFactoryInstance = dFactory
        return dFactory
    }

    static void resetConfigForTest() {
        dFactoryInstance = dFactoryInstance.getOriginalConfiguration()
    }

    SimpleFactory getOriginalConfiguration() {
        return this
    }

    def <T> T instanceForCallerOf(Class caller, Class<T> aClass) {
        def configuration = configurations.get(caller)
        if(configuration == null){
            return aClass.newInstance()
        }
        return configuration.get(aClass)
    }

    def Configuration configurationFor(Class<TestGeneratorForTestDescription> classUnderConfiguration) {
        def configuration = get(classUnderConfiguration)
        if(configuration == null){
            configuration = new Configuration(classUnderConfiguration)
            put(classUnderConfiguration, configuration)
        }

        return configuration
    }
}
