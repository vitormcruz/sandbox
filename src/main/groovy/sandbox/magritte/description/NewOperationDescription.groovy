package sandbox.magritte.description

interface NewOperationDescription extends Description{
    NewOperationDescription method(String methodName, Description... descriptions);
    NewOperationDescription forConstructor();
}
