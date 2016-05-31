package sandbox.magritte.description.recordingDescription

import org.junit.Test
import sandbox.magritte.description.util.InterfaceForRecording

import static groovy.test.GroovyAssert.shouldFail

class InterfaceSpecificRecordedMessageTest {

    @Test
    def void "RecordedMessage fails with a common error if it cannot be sent to an object that implements the interface provided"(){
        [[message: "inexistentMethod", args: null],
         [message: "interface_method2", args: null],
         [message: "interface_method3", args: [1]],
         [message: "interface_method4", args: ["test"]],
         [message: "interface_method4", args: ["test", new Date()]]].each { testCase ->
            def MissingMethodException ex = shouldFail(MissingMethodException, { new InterfaceSpecificRecordedMessage(testCase.message,
                                                                                                     testCase.args,
                                                                                                     InterfaceForRecording) })
            assert ex.method == testCase.message
        }
    }

    @Test
    def void "Creating a RecordedMessage with a valid message and arguments to the interface provided"(){
        def mockObject = [interface_method3 : { a, b -> return a + b}]
        def result = new InterfaceSpecificRecordedMessage("interface_method3", [1, 2], InterfaceForRecording).sendMyselfTo(mockObject)
        assert 3 == result
    }

    @Test
    def void "Creating a RecordedMessage with a valid message with varargs correctly filled, but with only one value for the vararg argument"(){
        def mockObject = [interface_method4 : {text, date, integer -> return "${text}${date}${integer}"}]
        def date = new Date()
        def result = new InterfaceSpecificRecordedMessage("interface_method4", ["test", date, 1], InterfaceForRecording).sendMyselfTo(mockObject)
        assert "test${date}1" == result
    }

    @Test
    def void "Creating a RecordedMessage with a valid message with varargs correctly filled with N values for the vararg argument"(){
        def mockObject = [interface_method4 : {text, date, i1, i2, i3 -> return "${text}${date}${i1}${i2}${i3}"}]
        def date = new Date()
        def result = new InterfaceSpecificRecordedMessage("interface_method4", ["test", date, 1,4,8], InterfaceForRecording).sendMyselfTo(mockObject)
        assert "test${date}148" == result
    }
}
