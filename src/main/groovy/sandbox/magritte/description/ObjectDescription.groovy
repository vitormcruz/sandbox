package sandbox.magritte.description


interface ObjectDescription extends Description{
    ObjectDescription accessor(String accessor);
    ObjectDescription beRequired();
    ObjectDescription defaultValue(defaultValue);
    ObjectDescription label(label);
}
