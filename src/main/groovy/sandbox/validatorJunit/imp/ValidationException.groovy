package sandbox.validatorJunit.imp

import sandbox.validatorJunit.ResultInterface

class ValidationException extends RuntimeException{

    def final ResultInterface result

    ValidationException(ResultInterface result) {
        this.result = result
    }

    def hasError(String errorMessage) {
        return result.failures.find {it.message.equals(errorMessage)} != null
    }
}
