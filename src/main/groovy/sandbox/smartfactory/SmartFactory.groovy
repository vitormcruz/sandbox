package sandbox.smartfactory

import sandbox.magritte.testGenerator.TestGeneratorForTestDescription

/**
 */
class SmartFactory implements Map<Class, Configuration>{
    private static SmartFactory dFactoryInstance = new SmartFactory()

    @Delegate
    private Map<Class, Configuration> configurations = new Hashtable<Class, Configuration>()

    def static SmartFactory instance(){
        return dFactoryInstance
    }

    static SmartFactoryForTest configureForTest() {
        def dFactory = new SmartFactoryForTest(dFactoryInstance)
        dFactoryInstance = dFactory
        return dFactory
    }

    static void resetConfigForTest() {
        dFactoryInstance = dFactoryInstance.getOriginalConfiguration()
    }

    SmartFactory getOriginalConfiguration() {
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
