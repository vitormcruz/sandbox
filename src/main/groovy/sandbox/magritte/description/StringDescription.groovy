package sandbox.magritte.description

interface StringDescription extends ObjectDescription{

    StringDescription beNotBlank()
    StringDescription maxSize(Integer maxSize)

}