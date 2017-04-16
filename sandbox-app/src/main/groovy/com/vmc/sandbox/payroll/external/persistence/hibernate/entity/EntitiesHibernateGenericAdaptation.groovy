package com.vmc.sandbox.payroll.external.persistence.hibernate.entity

import org.hibernate.SessionFactory

class EntitiesHibernateGenericAdaptation {

    static adaptIdentifiableEntities(SessionFactory sessionFactory){
//        teste(sessionFactory.getAllClassMetadata().collect {it.key as Class})
    }

    public static void teste(entities) {
//        entities.each{
//            it.metaClass.getId = {return delegate.@sandbox_payroll_IdentifiableTrait__id}
//
//        }
    }

}
