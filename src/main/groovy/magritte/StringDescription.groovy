package magritte

class StringDescription extends AbstractVisitableDescription implements MagnitudeDescription {

    @Override
    StringDescription defaultValue(defaultValue) {
        return addConfigurationMessageSend("acessor", [defaultValue])
    }

    @Override
    StringDescription label(label) {
        return addConfigurationMessageSend("label", label)
    }
}
