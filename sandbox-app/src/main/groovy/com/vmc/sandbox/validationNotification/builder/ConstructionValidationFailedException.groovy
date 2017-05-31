package com.vmc.sandbox.validationNotification.builder


class ConstructionValidationFailedException extends RuntimeException{

    ConstructionValidationFailedException() {
    }

    ConstructionValidationFailedException(String var1) {
        super(var1)
    }

    ConstructionValidationFailedException(String var1, Throwable var2) {
        super(var1, var2)
    }

    ConstructionValidationFailedException(Throwable var1) {
        super(var1)
    }

    ConstructionValidationFailedException(String var1, Throwable var2, boolean var3, boolean var4) {
        super(var1, var2, var3, var4)
    }
}
