package sandbox.magritte


//TODO make this guy implement everything by default, so that subclasses do not need to implement themselfs only calling addConfigurationMessageSend
abstract class AbstractVisitableDescription implements Description {

    //TODO encapsular messagesSend????
    /**
     * A map whose key is the message send and the value is a ordered array of parameters.
     */
    protected def messagesSend = [:]

    @Override
    Description beRequired() {
        messagesSend.put("beRequired", [null])
        return this
    }

    @Override
    Description acessor(String acessor) {
        messagesSend.put("acessor", [acessor])
        return this
    }

    @Override
    abstract Description defaultValue(defaultValue)

    AbstractVisitableDescription addConfigurationMessageSend(message, params){
        messagesSend.put(message, params)
        return this
    }

    //TODO change the name?
    void accept(aDescriptorVisitor){
        messagesSend.each {aDescriptorVisitor."${it.key}"(it.value)}
    }
}
