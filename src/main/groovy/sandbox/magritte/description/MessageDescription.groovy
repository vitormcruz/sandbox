package sandbox.magritte.description

interface MessageDescription extends Description{

    public final Integer FIRST = 1
    public final Integer SECOND = 2
    public final Integer THIRD = 3
    public final Integer FOURTH = 4
    public final Integer FIFTH = 5
    public final Integer SIXTH = 6
    public final Integer SEVENTH = 7
    public final Integer EIGHTH = 8
    public final Integer NINTH = 9
    public final Integer TENTH = 10
    //More!?!?! WTF!!! Refactor, please. If you really can't do much about it, use the number instead of the constant,
    //it is just an alias anyway.

    MessageDescription named(String methodName);
    MessageDescription withParameter(number, name, Description description);
}
