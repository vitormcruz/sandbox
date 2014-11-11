package magritte

import magritte.test.TestSuitDescription
import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail

/**
 * Tests the description of test suits.
 */
class TestSuitDescriptionTest{

    @Test
    def void "Create description for null class"(){
        def ex = shouldFail(IllegalArgumentException, { TestSuitDescription.forClass(null) })
        assert ex.message == "Cannot create a test suit description for null class"
    }
}
