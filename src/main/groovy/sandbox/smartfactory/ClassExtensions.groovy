package sandbox.smartfactory

class ClassExtensions {

    //TODO I wnat to remove this calle from here....... :(
    public static <T> T smartNewFor(Class<T> aClass, Class caller){
        return SmartFactory.instance().instanceForCallerOf(caller, aClass)
    }

    //TODO to implement...
    public static <T> T smartNewFor(Class<T> aClass, Class caller, Object... params){
        return aClass.newInstance(params)
    }
}
