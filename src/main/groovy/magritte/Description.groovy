package magritte


interface Description {

    Description acessor(String acessor);
    Description beRequired();
    Description defaultValue(defaultValue);
}