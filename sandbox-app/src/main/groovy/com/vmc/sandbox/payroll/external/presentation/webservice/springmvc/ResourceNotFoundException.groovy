package com.vmc.sandbox.payroll.external.presentation.webservice.springmvc

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value=HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException{
}
