package sandbox.magritte.imp


class PlaybackVerifier {

    private List<String> methodOrderExpected = []
    private List<String> methodOrderObtained = []
    private List<List> argumentOrderExpected = []
    private List<List> argumentOrderObtained = []

    def expectedMethodOrder(List<String> methodOrder){
        this.methodOrderExpected.addAll(methodOrder)
    }

    def expectedArgumentOrder(List<List> argumentOrder) {
        this.argumentOrderExpected.addAll(argumentOrder)
    }

    def methodMissing(String methodName, args) {
        methodOrderObtained.add(methodName)
        argumentOrderObtained.add(args as List)
    }

    def verifyPlayback(){
        assert methodOrderExpected == methodOrderObtained
        assert argumentOrderExpected == argumentOrderObtained
    }
}
