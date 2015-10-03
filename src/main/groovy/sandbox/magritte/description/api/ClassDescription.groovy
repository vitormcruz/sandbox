package sandbox.magritte.description.api

import sandbox.magritte.description.Description
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.description.TestDescription

import static sandbox.magritte.description.builder.DescriptionFactory.New

/**
 */
class ClassDescription {

    public static TestDescription testsValidatesThat(Class classUnderTest, Description... descriptions){
        return New(TestDescription).descriptionsFor(classUnderTest, descriptions)
    }

    public static OperationDescription constructorDescribed(Class classUnderTest){
        return New(OperationDescription).forConstructor()
    }
}
