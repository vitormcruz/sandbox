package com.vmc.smartfactory

class ClassExtensions {

    public static <T> T smartNewFor(Class<T> aClass, Class caller){
        return SmartFactory.instance().instanceForCallerOf(caller, aClass)
    }

    //TODO to implement...
    public static <T> T smartNewFor(Class<T> aClass, Class caller, Object... params){
        return aClass.newInstance(params)
    }
}
