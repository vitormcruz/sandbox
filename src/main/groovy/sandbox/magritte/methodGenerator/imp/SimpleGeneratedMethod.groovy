package sandbox.magritte.methodGenerator.imp

import sandbox.magritte.methodGenerator.GeneratedMethod


class SimpleGeneratedMethod implements GeneratedMethod{
    protected String methodName
    protected Closure methodBody

    SimpleGeneratedMethod() {
    }

    SimpleGeneratedMethod(String methodName, Closure methodBody) {
        this.methodName = methodName
        this.methodBody = methodBody
    }

    @Override
    String getMethodName() {
        return methodName
    }

    @Override
    Closure getMethodBody() {
        return methodBody
    }

    @Override
    void teachMyselfTo(Object grasshopper) {
        grasshopper.class.metaClass."${getMethodName()}" << getMethodBody()
    }
}
