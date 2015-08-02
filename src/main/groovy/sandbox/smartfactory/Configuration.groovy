package sandbox.smartfactory

/**
 */
class Configuration implements Map<Class, Object>{

    @Delegate
    Map<Class, Object> definitions = new Hashtable<Class, Object>()

    private Class configuredClass

    Configuration(Class configuredClass) {
        this.configuredClass = configuredClass
    }
}
