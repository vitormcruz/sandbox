package magritte

class StringDescription extends Description {

    boolean required

    Description beRequired() {
        required = true
        return this
    }
}
