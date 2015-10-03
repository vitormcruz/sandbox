package sandbox.magritte.description

interface OperationDescription extends Description{

    public final Integer FIRST = 1
    public final Integer SECOND = 2
    public final Integer THIRD = 3
    public final Integer FOURTH = 4

    OperationDescription named(String methodName);
    OperationDescription forConstructor();
    OperationDescription withParameter(number, Description description);
}
