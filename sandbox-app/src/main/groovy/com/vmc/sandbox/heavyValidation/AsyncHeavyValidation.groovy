package com.vmc.sandbox.heavyValidation

interface AsyncHeavyValidation {

    def doValidation(NotifyProgress notifyFunction)

    public interface NotifyProgress{
        void notifyProgress(Float progress)
    }
}
