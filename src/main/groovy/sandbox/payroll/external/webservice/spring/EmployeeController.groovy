package sandbox.payroll.external.webservice.spring
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.querydsl.core.support.QueryBase
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.*
import sandbox.payroll.business.ModelSnapshot
import sandbox.payroll.business.entity.Employee
import sandbox.payroll.business.entity.repository.EmployeeRepository
import sandbox.payroll.business.entity.repository.entityQuery.QEmployee

@RequestMapping(value = "payroll")
@RestController
class EmployeeController{

    private EmployeeRepository employeeRepository = EmployeeRepository.smartNewFor(EmployeeController)
    private ModelSnapshot model = ModelSnapshot.smartNewFor(EmployeeController)
    private ModelMapper modelMapper = ModelMapper.smartNewFor(EmployeeController)

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    Employee newEmployee(@RequestBody Employee newEmployee) {
        employeeRepository.add(newEmployee)
        model.save()
        return newEmployee;
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PATCH)
    Employee changeEmployee(@PathVariable Long employeeId, @RequestBody String changedAttributes) {
        def changedEmployee = employeeRepository.find { QueryBase query, QEmployee qEmployee ->
            query.where(qEmployee.id.eq(employeeId))
        }
        JsonNode changedAttributesNode = new ObjectMapper().readTree(changedAttributes)
        modelMapper.map(changedAttributesNode, changedEmployee)
        employeeRepository.update(changedEmployee)
        model.save()
        return changedEmployee;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    Collection<Employee> listEmployees() {
        return employeeRepository
    }
}
