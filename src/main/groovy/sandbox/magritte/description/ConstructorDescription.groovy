package sandbox.magritte.description

interface ConstructorDescription extends MessageDescription{

    public final Integer FIRST = 1
    public final Integer SECOND = 2
    public final Integer THIRD = 3
    public final Integer FOURTH = 4

    ConstructorDescription named(String methodName);
    ConstructorDescription forConstructor();
    ConstructorDescription withParameter(number, name, Description description);
}
