package com.vmc.sandbox.payroll.external.config

abstract class HibernateResources {

    public static List<String> listOfMappings(){
        return ["com/vmc/sandbox/payroll/external/interfaceAdapter/persistence/hibernate/mapping/Payroll.hbm.xml"]
    }
}
