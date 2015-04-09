package sandbox.magritte.description

interface StringDescription extends BaseDescription{

    StringDescription beNotBlank()
    StringDescription maxSize(Integer maxSize)

}