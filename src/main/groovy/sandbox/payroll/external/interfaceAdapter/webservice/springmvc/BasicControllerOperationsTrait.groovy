package sandbox.payroll.external.interfaceAdapter.webservice.springmvc
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper
import sandbox.validationNotification.ApplicationValidationNotifier

trait BasicControllerOperationsTrait {

    private ModelMapper modelMapper = ModelMapper.smartNewFor(BasicControllerOperationsTrait)

    /**
     * Returns a patched entity based on what was passed by aEntity parameter: if it is a class, then creates a new
     * class and patches it; if it is an object, it simply patches it. Uses jsonRepresentation passed by parameter to do
     * the patching.
     */
    public Object mapEntityFromJson(aEntity, jsonRepresentation){
        JsonNode changedAttributesNode = new ObjectMapper().readTree(jsonRepresentation)

        /**
         * Creates an identity for the JsonNode representation because of this:
         *
         * "ModelMapper maintains a TypeMap for each source and destination type, containing the mappings bewteen the
         * two types. For “generic” types such as JsonNode this can be problematic since the structure of a JsonNode
         * can vary. In order to distinguish structurally different JsonNodes that map to the same destination type,
         * we can provide a type map name to ModelMapper." http://modelmapper.org/user-manual/jackson-integration/
         */
        def identity = jsonIdentifier(changedAttributesNode)
        return modelMapper.map(changedAttributesNode, aEntity, identity)
    }

    public String jsonIdentifier(JsonNode jsonNode){
        if(!jsonNode.isContainerNode()){
            return ""
        }

        String intermediaryJsonIdentifier = jsonNode.fieldNames().join("")
        jsonNode.each { intermediaryJsonIdentifier += jsonIdentifier(it) }
        return intermediaryJsonIdentifier
    }

    public RestControllerValidationListener getValidationListener() {
        def listener = new RestControllerValidationListener()
        ApplicationValidationNotifier.addObserver(listener)
        listener
    }

    public Object getResource(long employeeId, resourceRepository) {
        def resource = resourceRepository.get(employeeId)
        if (!resource) throw new ResourceNotFoundException()
        return resource
    }

}