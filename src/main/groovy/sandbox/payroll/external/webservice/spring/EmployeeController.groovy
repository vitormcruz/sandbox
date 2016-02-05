package sandbox.payroll.external.webservice.spring
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.hibernate.SessionFactory
import org.modelmapper.ModelMapper
import org.modelmapper.jackson.JsonNodeValueReader
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.web.bind.annotation.*
import sandbox.payroll.business.ModelSnapshot
import sandbox.payroll.business.entity.Employee
import sandbox.payroll.business.entity.repository.EmployeeRepository
import sandbox.payroll.external.persistence.hibernate.HibernatePersistentModelSnapshot
import sandbox.payroll.external.persistence.hibernate.repository.HibernateEmployeeRepository

@RequestMapping(value = "payroll")
@RestController
class EmployeeController implements InitializingBean{

    @Autowired
    private SessionFactory sessionFactory

    @Autowired
    private PlatformTransactionManager transactionManager

    private EmployeeRepository employeeRepository
    private ModelSnapshot model
    private ModelMapper modelMapper = new ModelMapper()

    @Override
    void afterPropertiesSet() throws Exception {
        employeeRepository = new HibernateEmployeeRepository(sessionFactory, transactionManager)
        model = new HibernatePersistentModelSnapshot(transactionManager)
        modelMapper.getConfiguration().addValueReader(new JsonNodeValueReader());
        model.add(employeeRepository)
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    Employee newEmployee(@RequestBody Employee newEmployee) {
        employeeRepository.add(newEmployee)
        model.save()
        return newEmployee;
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PATCH)
    Employee changeEmployee(@PathVariable Long employeeId, @RequestBody String changedAttributes) {
        def changedEmployee = employeeRepository.find { it.id == employeeId }
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
