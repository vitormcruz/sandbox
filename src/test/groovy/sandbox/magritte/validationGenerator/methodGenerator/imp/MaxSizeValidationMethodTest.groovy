package sandbox.magritte.validationGenerator.methodGenerator.imp

import org.junit.Ignore
import org.junit.runner.RunWith
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.magritte.testGenerator.description.TestDescription
import sandbox.magritte.testGenerator.junit.JUnit4TestGeneratorRunner

import static sandbox.magritte.description.builder.DescriptionFactory.New

@RunWith(JUnit4TestGeneratorRunner)
@Ignore("I will have to provide a way to describe a method. Look at magritte implementation of pharo to see what they have done")
class MaxSizeValidationMethodTest {
    
    @DescriptionModelDefinition
    def myTestDescription(){
        return New(TestDescription).descriptionsFor(MaxSizeValidationMethod,
                                                    New(StringDescription).accessor("accessor").maxSize(50).beRequired()
//                                                    New(StringDescription).accessor("email").beRequired()
        )
    }
    
    
}
