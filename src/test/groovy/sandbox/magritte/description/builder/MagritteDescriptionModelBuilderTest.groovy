package sandbox.magritte.description.builder

import org.junit.Test
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.description.util.PlaybackVerifier

import static groovy.test.GroovyAssert.shouldFail
import static sandbox.magritte.description.builder.DescriptionFactory.New

class MagritteDescriptionModelBuilderTest {

    private MagritteDescriptionModelBuilder modelBuilder = new MagritteDescriptionModelBuilder()

    @Test
    def void "Build a description model from a class without any description model definition"() {
        descriptionModelShouldBeEmptyFor(new ClassWithoutDescriptionModelDefinition())
    }

    public static class ClassWithoutDescriptionModelDefinition { }

    @Test
    def void "Build a description model from a class with one empty description model definition"() {
        descriptionModelShouldBeEmptyFor(new ClassWithOneDescriptionModel([]))
    }

    @Test
    def void "Build a description model from a class with one null description model definition"() {
        descriptionModelShouldBeEmptyFor(new ClassWithOneDescriptionModel(null))
    }

    @Test
    def void "Build a description model from a class with N empty description model definition"() {
        descriptionModelShouldBeEmptyFor(new ClassWithNDescriptionModels([], []))
    }

    @Test
    def void "Build a description model from a class with N null description model definition"() {
        descriptionModelShouldBeEmptyFor(new ClassWithNDescriptionModels(null, null))
    }

    @Test
    def void "Build a description model from a class with one non empty description model definition"() {
        def model = New(StringDescription).beNotBlank().maxSize(10)
        def classParentWithDescription = new ClassWithOneDescriptionModel(model)
        def expectedMessageSent = ["beNotBlank", "maxSize"]
        def expectedArgumentOrder = [[], [10]]

        descriptionModelForClassShouldHave(classParentWithDescription, expectedMessageSent, expectedArgumentOrder)
    }

    @Test
    def void "Build a description model from a class with N description model definitions"() {
        def model1 = New(StringDescription).beNotBlank().maxSize(10)
        def model2 = New(NumberDescription).beNatural().beRequired()
        def classParentWithDescription = new ClassWithNDescriptionModels(model1, model2)
        def expectedMessageSent = ["beNatural", "beRequired", "beNotBlank", "maxSize"]
        def expectedArgumentOrder = [[], [], [], [10]]

        descriptionModelForClassShouldHave(classParentWithDescription, expectedMessageSent, expectedArgumentOrder)
    }

    @Test
    def void "Build a description model from a class with N description model definitions, each with N descriptions"() {
        def model1 = [New(StringDescription).beNotBlank().maxSize(10),
                      New(StringDescription).accessor("testAccessor1").maxSize(20)]
        def model2 = [New(NumberDescription).beNatural().beRequired(),
                      New(StringDescription).accessor("testAccessor2").maxSize(40),
                      New(StringDescription).maxSize(1000).beNotBlank()]
        def classParentWithDescription = new ClassWithNDescriptionModels(model1, model2)
        def expectedMessageSent = ["beNatural", "beRequired", "accessor", "maxSize", "maxSize", "beNotBlank",
                                   "beNotBlank", "maxSize", "accessor", "maxSize"]
        def expectedArgumentOrder = [[], [], ["testAccessor2"], [40], [1000], [], [], [10], ["testAccessor1"], [20]]

        descriptionModelForClassShouldHave(classParentWithDescription, expectedMessageSent, expectedArgumentOrder)
    }

    @Test
    def void "Build a description model from a class that return one description not in a collection should return a collection with this description"() {
        def model = New(StringDescription).beNotBlank().maxSize(10)
        def modelObtained = modelBuilder.forObject(new ClassWithOneDescriptionModel(model))

        assert modelObtained == [model]
    }

    @Test
    def void "Build a description model from a class with a description model definition in the parent class"() {
        def model = New(StringDescription).beNotBlank().maxSize(10)
        def classParentWithDescription = new ClassWithADescriptionModelFromParent(model)
        def expectedMessageSent = ["beNotBlank", "maxSize"]
        def expectedArgumentOrder = [[], [10]]

        descriptionModelForClassShouldHave(classParentWithDescription, expectedMessageSent, expectedArgumentOrder)
    }

    public static class ClassWithADescriptionModelFromParent extends ClassWithOneDescriptionModel{

        ClassWithADescriptionModelFromParent(descriptionModel) {
            super(descriptionModel)
        }
    }

    @Test
    def void "Build a description model from a class with a description model definition in the grand parent class"() {
        def model = New(StringDescription).beNotBlank().maxSize(10)
        def classParentWithDescription = new ClassWithADescriptionModelFromGrandParent(model)
        def expectedMessageSent = ["beNotBlank", "maxSize"]
        def expectedArgumentOrder = [[], [10]]
        descriptionModelForClassShouldHave(classParentWithDescription, expectedMessageSent, expectedArgumentOrder)
    }

    public static class ClassWithADescriptionModelFromGrandParent extends ClassWithOneDescriptionModel{

        ClassWithADescriptionModelFromGrandParent(descriptionModel) {
            super(descriptionModel)
        }
    }

    @Test
    def void "Build a description model from a class with a description model definition rising an exception"() {
        shouldFail(IllegalArgumentException, {modelBuilder.forObject(new ClassThatThrowExceptionUponDescriptionModelCall())})
    }

    public static class ClassThatThrowExceptionUponDescriptionModelCall{

        @DescriptionModelDefinition
        public myDescription(){
            throw new IllegalArgumentException()
        }
    }

    public static class ClassWithOneDescriptionModel{

        private Object descriptionModel

        ClassWithOneDescriptionModel(descriptionModel) {
            this.descriptionModel = descriptionModel
        }

        @DescriptionModelDefinition
        public myDescription(){
            return descriptionModel
        }
    }

    public static class ClassWithNDescriptionModels extends ClassWithOneDescriptionModel{
        private Object secondDescriptionModel

        ClassWithNDescriptionModels(Object descriptionModel, secondDescriptionModel) {
            super(descriptionModel)
            this.secondDescriptionModel = secondDescriptionModel
        }

        @DescriptionModelDefinition
        public mySecondDescription(){
            return secondDescriptionModel
        }

    }

    void descriptionModelForClassShouldHave(describedClass, expectedMessageSent, expectedArgumentOrder) {
        def playbackVerifier = new PlaybackVerifier(modelBuilder.forObject(describedClass))
        playbackVerifier.expectedMethodOrder(expectedMessageSent)
        playbackVerifier.expectedArgumentOrder(expectedArgumentOrder)
        playbackVerifier.verifyPlayback()
    }

    private PlaybackVerifier descriptionModelShouldBeEmptyFor(describedClass) {
        def playbackVerifier = new PlaybackVerifier(modelBuilder.forObject(describedClass))
        assert playbackVerifier.nothingWasPlayed() : "Should receive an empty model."
    }
}
