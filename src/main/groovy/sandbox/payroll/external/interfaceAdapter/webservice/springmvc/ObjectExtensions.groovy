package sandbox.payroll.external.interfaceAdapter.webservice.springmvc

class ObjectExtensions {

    public static Object applyMap(Object anObject, Map map){
        ObjectMapping objectMapping = ObjectMapping.smartNewFor(ObjectExtensions)
        objectMapping.map(anObject, map)
        return anObject
    }
}
