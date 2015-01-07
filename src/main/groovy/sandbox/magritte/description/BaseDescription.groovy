package sandbox.magritte.description


interface BaseDescription extends Description{
    Description acessor(String acessor);
    Description beRequired();
    Description defaultValue(defaultValue);
    Description label(label);
}
