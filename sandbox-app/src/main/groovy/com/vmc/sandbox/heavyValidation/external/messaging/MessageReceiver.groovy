package com.vmc.sandbox.heavyValidation.external.messaging

class MessageReceiver {

    static def listerners = []

    public void receiveMessage(String message) {
        (1..10).each { val ->
            sleep(1200)
            listerners.each {it.notifyProgress(val/10)}
        }
    }

    def static void addListener(listener){
        listerners.add(listener)
    }


}
