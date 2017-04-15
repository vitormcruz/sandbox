package com.vmc.sandbox.payroll.external.interfaceAdapter.simpleConverter

class ObjectExtensions {

    public static Object applyMap(Object anObject, Map map){
        SimpleObjectMapping objectMapping = SimpleObjectMapping.smartNewFor(ObjectExtensions)
        objectMapping.map(anObject, map)
        return anObject
    }
}
