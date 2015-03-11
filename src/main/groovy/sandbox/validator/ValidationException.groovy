package sandbox.validator

class ValidationException extends RuntimeException{

    def final ResultInterface result

    ValidationException(ResultInterface result) {
        this.result = result
    }
}
