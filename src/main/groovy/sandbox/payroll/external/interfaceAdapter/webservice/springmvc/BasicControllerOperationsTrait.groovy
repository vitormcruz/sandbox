package sandbox.payroll.external.interfaceAdapter.webservice.springmvc

import sandbox.validationNotification.ApplicationValidationNotifier

trait BasicControllerOperationsTrait {

    public RestControllerValidationListener getValidationListener() {
        def listener = new RestControllerValidationListener()
        ApplicationValidationNotifier.addObserver(listener)
        listener
    }

    public Object getResource(long employeeId, resourceRepository) {
        def resource = resourceRepository.get(employeeId)
        if (!resource) throw new ResourceNotFoundException()
        return resource
    }

}