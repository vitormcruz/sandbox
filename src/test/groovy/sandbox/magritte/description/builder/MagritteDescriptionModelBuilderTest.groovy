package sandbox.magritte.description.builder
import org.junit.Ignore
import org.junit.Test
import sandbox.magritte.description.DescriptionContainer
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.util.PlaybackVerifier

import static sandbox.magritte.description.builder.DescriptionFactory.New

class MagritteDescriptionModelBuilderTest {

    @Test
    def void "Build a description model from a class without any description model definition"() {
        DescriptionModelShouldBeEmptyFor(new ClassWithoutDescriptionModelDefinition())
    }

    public static class ClassWithoutDescriptionModelDefinition { }

    @Test
    def void "Build a description model from a class with one empty description model definition"() {
        DescriptionModelShouldBeEmptyFor(new ClassWithOneEmptyDescriptionModel())
    }

    public static class ClassWithOneEmptyDescriptionModel{
        @DescriptionModelDefinition
        public myDescription(){
            return New(DescriptionContainer)
        }

    }

    @Test
    def void "Build a description model from a class with one null description model definition"() {
        DescriptionModelShouldBeEmptyFor(new ClassWithOneNullDescriptionModel())
    }

    public static class ClassWithOneNullDescriptionModel{
        @DescriptionModelDefinition
        public myDescription(){
            return null
        }

    }

    @Test
    @Ignore
    def void "Build a description model from a class with a description model definition rising an exception"() {    }

    @Test
    def void "Build a description model from a class with N empty description model definition"() {
        DescriptionModelShouldBeEmptyFor(new ClassWithNEmptyDescriptionModel())
    }

    public static class ClassWithNEmptyDescriptionModel{
        @DescriptionModelDefinition
        public myDescription1(){
            return New(DescriptionContainer)
        }

        @DescriptionModelDefinition
        public myDescription2(){
            return New(DescriptionContainer)
        }

    }

    @Test
    def void "Build a description model from a class with N null description model definition"() {
        DescriptionModelShouldBeEmptyFor(new ClassWithNNullDescriptionModel())
    }

    public static class ClassWithNNullDescriptionModel{
        @DescriptionModelDefinition
        public myDescription1(){
            return null
        }

        @DescriptionModelDefinition
        public myDescription2(){
            return null
        }

    }

    @Test
    @Ignore
    def void "Build a description model from a class with one non empty description model definition"() {    }

    private PlaybackVerifier DescriptionModelShouldBeEmptyFor(descriptedClass) {
        def playbackVerifier = new PlaybackVerifier()
        MagritteDescriptionModelBuilder.forObject(descriptedClass).accept(playbackVerifier)
        assert playbackVerifier.nothingWasPlayed() : "Should receive an empty model."
    }

    @Test
    @Ignore
    def void "Build a description model from a class with one description model definition from an extension method"() {}

    @Test
    @Ignore
    def void "Build a description model from a class with N description model definitions"() {}
}
