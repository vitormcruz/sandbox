package magritte

class StringDescription extends Description {

    //TODO acho que dá para jogar tudo para cima.
    /**
     * A map whose key is the message send and the value is a ordered array of parameters.
     */
    def messagesSend = [:]

    Description beRequired() {
        messagesSend.put("beRequired", [null])
        return this
    }

    Description acessor(String acessor) {
        messagesSend.put("acessor", [acessor])
        return this
    }

    Description defaultValue(String defaultValue) {
        messagesSend.put("acessor": [defaultValue])
        return this
    }
}
