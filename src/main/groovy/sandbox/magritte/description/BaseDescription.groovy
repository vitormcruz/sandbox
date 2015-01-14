package sandbox.magritte.description


interface BaseDescription extends Description{
    Description accessor(String accessor);
    Description beRequired();
    Description defaultValue(defaultValue);
    Description label(label);
}
