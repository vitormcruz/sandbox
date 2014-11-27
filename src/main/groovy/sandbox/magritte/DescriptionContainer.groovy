package sandbox.magritte

//TODO Should implement Description?
//TODO should be visitable just like other descriptions, extending AbstractVisitableDescription?
class DescriptionContainer extends AbstractVisitableDescription implements IDescriptionContainer{

    //TODO should be here or in description itself? A description should know the class it describes?
    Class forClass


    def DescriptionContainer(Class forClass, Description... descriptions) {
        this.forClass = forClass
        addAll(descriptions as Collection)
    }

    def DescriptionContainer(Description... descriptions) {
        addAll(descriptions as Collection)
    }

    @Override
    DescriptionContainer addAll(Collection<? extends Description> collection) {
        return addConfigurationMessageSend("addAll", collection)
    }

    @Override
    Description defaultValue(Object defaultValue) {
        return null
    }

    @Override
    Description label(Object label) {
        return null
    }
}
