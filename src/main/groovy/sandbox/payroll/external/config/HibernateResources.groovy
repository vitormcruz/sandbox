package sandbox.payroll.external.config

abstract class HibernateResources {

    public static List<String> listOfMappings(){
        return ["sandbox/payroll/external/interfaceAdapter/persistence/hibernate/mapping/Payroll.hbm.xml"]
    }
}
