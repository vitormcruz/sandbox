package sandbox.magritte.description

//TODO replaced by MessageDescription. Remove when appropriate.
interface ObjectDescription extends Description{
    ObjectDescription accessor(String accessor);
    ObjectDescription beRequired();
    ObjectDescription defaultValue(defaultValue);
    ObjectDescription label(label);
}
