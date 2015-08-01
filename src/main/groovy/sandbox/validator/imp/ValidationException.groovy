package sandbox.validator.imp

import sandbox.validator.ResultInterface

class ValidationException extends RuntimeException{

    def final ResultInterface result

    ValidationException(ResultInterface result) {
        this.result = result
    }
}
