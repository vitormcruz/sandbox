package sandbox.validator.imp

import sandbox.validator.ResultInterface

class ValidationException extends RuntimeException{

    def final ResultInterface result

    ValidationException(ResultInterface result) {
        this.result = result
    }

    def hasError(String errorMessage) {
        return result.failures.find {it.message.equals(errorMessage)} != null
    }
}
