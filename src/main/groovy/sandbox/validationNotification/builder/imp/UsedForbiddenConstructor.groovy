package sandbox.validationNotification.builder.imp


class UsedForbiddenConstructor extends RuntimeException{

    UsedForbiddenConstructor(String message) {
        super(message)
    }

}
