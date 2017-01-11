package com.vmc.smartfactory

/**
 */
class Configuration implements Map<Class, Object>{

    @Delegate
    Map<Class, Object> definitions = new Hashtable<Class, Object>()

    Class put(Class key, Object value){
        if(!(value instanceof Closure)){
            return definitions.put(key, {return value})
        }

        return definitions.put(key, value)
    }


}
