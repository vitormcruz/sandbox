package sandbox.smartfactory
/**
 */
class SmartFactory {
    private static SmartFactory dFactoryInstance = new SmartFactory()

    private Map<String, Configuration> configurations = new Hashtable<Class, Configuration>()

    def static SmartFactory instance(){
        return dFactoryInstance
    }

    def <T> T instanceForCallerOf(Class caller, Class<T> aClass) {
        def configuration = configurationFor(caller.getName())
        if(!configuration || !configuration.get(aClass)){
            try {
                return aClass.newInstance()
            } catch (GroovyRuntimeException e) {
                return null
            }
        }

        return configuration.get(aClass)
    }

    def Configuration configurationFor(String context) {
        def configuration = getConfigurationThatMatches(context)
        if(configuration == null){
            configuration = new Configuration()
            configurations.put(context, configuration)
        }

        return configuration
    }

    private Configuration getConfigurationThatMatches(String context) {
        return configurations.entrySet().find {
            return new WildcardMatcher(it.key).match(context)
        }?.value
    }

    static public class WildcardMatcher {

        private String matcherString

        WildcardMatcher(String matcherString) {
            if(matcherString == "**"){
                this.matcherString = ".*"
            }else{
                this.matcherString = matcherString.replace(".*", "\\.?[\\w]*").replace("\\.?[\\w]**", "\\.?[\\w\\.]*")
            }
        }

        def Boolean match(String aString){
            return aString ==~ matcherString
        }
    }
}
