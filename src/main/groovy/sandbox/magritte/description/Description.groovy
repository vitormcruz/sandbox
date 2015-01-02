package sandbox.magritte.description


interface Description {

    Description acessor(String acessor);
    Description beRequired();
    Description defaultValue(defaultValue);
    Description label(label);
}