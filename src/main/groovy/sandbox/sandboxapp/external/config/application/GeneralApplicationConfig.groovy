package sandbox.sandboxapp.external.config.application

import sandbox.sandboxapp.Config
import sandbox.validationNotification.ApplicationValidationNotifier

class GeneralApplicationConfig implements Config {

    @Override
    public void configure() {
        ApplicationValidationNotifier.createCurrentListOfListeners()
    }

    @Override
    public void tearDown() {
        ApplicationValidationNotifier.destroyCurrentListOfListeners()
    }
}
