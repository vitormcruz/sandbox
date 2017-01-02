package com.vmc.smartfactory

/**
 */
class Configuration implements Map<Class, Object>{

    @Delegate
    Map<Class, Object> definitions = new Hashtable<Class, Object>()

}
