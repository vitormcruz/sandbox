package sandbox.magritte

//TODO Should implement Description?
class DescriptionContainer extends AbstractVisitableDescription implements IDescriptionContainer{

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
