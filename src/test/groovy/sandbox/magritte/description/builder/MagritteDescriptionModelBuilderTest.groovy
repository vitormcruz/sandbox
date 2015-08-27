package sandbox.magritte.description.builder
import org.junit.Ignore
import org.junit.Test
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.magritte.description.util.PlaybackVerifier

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
    def void "Build a description model from a class with N empty description model definition"() {
        descriptionModelShouldBeEmptyFor(new ClassWithNDescriptionModels([], []))
    }

    @Test
    def void "Build a description model from a class with N null description model definition"() {
        descriptionModelShouldBeEmptyFor(new ClassWithNDescriptionModels(null, null))
    }

    @Test
    @Ignore
    def void "Build a description model from a class with one non empty description model definition"() {    }

    @Test
    @Ignore
    def void "Build a description model from a class with N description model definitions"() {}

    @Test
    @Ignore
    def void "Build a description model from a class with one description model definition from an extension method"() {}

    @Test
    @Ignore
    def void "Build a description model from a class with a description model definition rising an exception"() {    }

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
