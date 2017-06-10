package com.vmc.sandbox.payroll.external.presentation.webservice.springmvc

import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier

trait BasicControllerOperationsTrait {

    public SparkControllerValidationListener getValidationListener() {
        def listener = new SparkControllerValidationListener()
        ApplicationValidationNotifier.addObserver(listener)
        return listener
    }

    public Object getResource(long employeeId, resourceRepository) {
        def resource = resourceRepository.get(employeeId)
        if (!resource) throw new ResourceNotFoundException()
        return resource
    }

}