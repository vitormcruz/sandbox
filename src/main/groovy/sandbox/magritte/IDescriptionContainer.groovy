package sandbox.magritte


//TODO Remove the "I" from this class and chage the name of DescriptionContainer to DescriptionContainerImp or similar
interface IDescriptionContainer extends Description{

    IDescriptionContainer addAll(Collection<? extends Description> collection);

}