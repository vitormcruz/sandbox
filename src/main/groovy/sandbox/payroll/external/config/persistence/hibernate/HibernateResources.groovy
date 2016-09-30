package sandbox.payroll.external.config.persistence.hibernate

abstract class HibernateResources {

    public static List<String> listOfMappings(){
        return ["sandbox/payroll/external/interfaceAdapter/persistence/hibernate/mapping/Employee.hbm.xml"]
    }
}
