package com.vmc.sandbox.payroll.external.presentation.webservice.springmvc

import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier

trait BasicControllerOperationsTrait {

    public WebServiceControllerValidationListener getValidationListener() {
        def listener = new WebServiceControllerValidationListener()
        ApplicationValidationNotifier.addObserver(listener)
        listener
    }

    public Object getResource(long employeeId, resourceRepository) {
        def resource = resourceRepository.get(employeeId)
        if (!resource) throw new ResourceNotFoundException()
        return resource
    }

}