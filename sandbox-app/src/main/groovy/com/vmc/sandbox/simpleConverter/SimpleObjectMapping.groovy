package com.vmc.sandbox.simpleConverter

//TODO Tests
class SimpleObjectMapping {

    private static Map<Class, Map<String, Closure>> objectMappings = [:]

    public void map(objectToMap, Map stringStringMap) {
        def objectMapping = getObjectMapping(objectToMap.getClass())

        stringStringMap.each {String key, value ->
            def mappingFunction = objectMapping.get(key)
            if(mappingFunction){
                mappingFunction(objectToMap, value)
            }else {
                objectToMap."set${key.capitalize()}"(value)
            }
        }

    }

    private LinkedHashMap getObjectMapping(aClass) {
        def objectMapping = objectMappings.get(aClass)
        if (!objectMapping) {
            objectMapping = [:]
            objectMappings.put(aClass, objectMapping)
        }
        return objectMapping
    }

    public getObjectMappingFor(Class aClass){
        return getObjectMapping(aClass)
    }
}
