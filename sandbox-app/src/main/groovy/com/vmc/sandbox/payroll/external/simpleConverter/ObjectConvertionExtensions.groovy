package com.vmc.sandbox.payroll.external.simpleConverter

class ObjectConvertionExtensions {

    public static Object applyMap(Object anObject, Map map){
        SimpleObjectMapping objectMapping = SimpleObjectMapping.smartNewFor(ObjectConvertionExtensions)
        objectMapping.map(anObject, map)
        return anObject
    }
}
