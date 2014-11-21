package magritte


//TODO make this guy implement everything by default, so that subclasses do not need to implement themselfs only calling addConfigurationMessageSend
abstract class AbstractVisitableDescription implements Description {

    //TODO encapsular messagesSend????
    /**
     * A map whose key is the message send and the value is a ordered array of parameters.
     */
    protected def messagesSend = [:]

    AbstractVisitableDescription beRequired() {
        messagesSend.put("beRequired", [null])
        return this
    }

    AbstractVisitableDescription acessor(String acessor) {
        messagesSend.put("acessor", [acessor])
        return this
    }

    abstract AbstractVisitableDescription defaultValue(defaultValue)

    AbstractVisitableDescription addConfigurationMessageSend(message, params){
        messagesSend.put(message, params)
        return this
    }

    //TODO change the name for
    void accept(aDescriptorVisitor){
        messagesSend.each {aDescriptorVisitor."${it.key}"(it.value)}
    }
}
