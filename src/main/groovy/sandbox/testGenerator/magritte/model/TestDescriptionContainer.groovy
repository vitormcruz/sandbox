package sandbox.testGenerator.magritte.model
import sandbox.magritte.Description
import sandbox.magritte.DescriptionContainer
//TODO Should implement Description?
class TestDescriptionContainer extends DescriptionContainer{

    //TODO should be here or in description itself? A description should know the class it describes?
    Class forClass


    def TestDescriptionContainer(Class forClass, Description... descriptions) {
        super(descriptions)
        this.forClass = forClass
    }

}
