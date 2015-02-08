package sandbox
/**
 */
class MessageReceiver {

    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     */
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
