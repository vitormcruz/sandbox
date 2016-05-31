package sandbox.payroll

import sandbox.magritte.description.recordingDescription.InterfaceSpecificRecordedMessage

class GenericBuilder {

    def T methodMissing(String nameOfMethodToRecord, args) {
        recordedMethods.add(new InterfaceSpecificRecordedMessage(nameOfMethodToRecord, args, interfaceBeenRecorded))
        return asTypeBeingRecorded()
    }
}
