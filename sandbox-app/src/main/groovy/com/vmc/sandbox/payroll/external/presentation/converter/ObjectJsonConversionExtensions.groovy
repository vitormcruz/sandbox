package com.vmc.sandbox.payroll.external.presentation.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.external.config.ServiceLocator

class ObjectJsonConversionExtensions {

    private static ObjectMapper mapper = ServiceLocator.getInstance().mapper()

    static String asJson(Object object){
        object.asJsonConverter().toJson()
    }

    static JsonConverter asJsonConverter(Object object){
        return new DefaultJsonConverter(object)
    }

    static JsonConverter asJsonConverter(Employee employee){
        return new EmployeeJsonConverter(employee)
    }

    public static class DefaultJsonConverter implements JsonConverter{

        private Object object

        DefaultJsonConverter(Object object) {
            this.object = object
        }

        @Override
        String toJson() {
            return mapper.writeValueAsString(object)
        }
    }
}
