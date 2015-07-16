package sandbox.dfactory

import sandbox.magritte.testGenerator.TestGeneratorForTestDescription

/**
 */
class DFactory implements Map<Class, Configuration>{
    private static DFactory dFactoryInstance = new DFactory()

    @Delegate
    private Map<Class, Configuration> configurations = new Hashtable<Class, Configuration>()

    def static DFactory instance(){
        return dFactoryInstance
    }

    static TestDFactory configureForTest() {
        def dFactory = new TestDFactory(dFactoryInstance)
        dFactoryInstance = dFactory
        return dFactory
    }

    static void resetConfigForTest() {
        dFactoryInstance = dFactoryInstance.getOriginalConfiguration()
    }

    DFactory getOriginalConfiguration() {
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
