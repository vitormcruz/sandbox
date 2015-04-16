package sandbox.magritte.description


interface BaseDescription extends Description{
    BaseDescription accessor(String accessor);
    BaseDescription beRequired();
    BaseDescription defaultValue(defaultValue);
    BaseDescription label(label);
}
