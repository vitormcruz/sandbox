package com.vmc.sandbox.validationNotification.builder.imp

import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import groovy.test.GroovyAssert
import org.junit.Test

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class DataSetBuilderUnitTest extends ValidationNotificationTestSetup{

    @Test
    def void "The DataSetBuilder class parameter must be provided"(){
        def ex = GroovyAssert.shouldFail IllegalArgumentException, {getDataSetBuilder(null, {})}
        assert ex.message == "A class to build must be provided"
    }

    @Test
    def void "The DataSetBuilder insertCommand parameter must be provided"(){
        def ex = GroovyAssert.shouldFail IllegalArgumentException, {getDataSetBuilder(BuildedClass, null)}
        assert ex.message == "An insertCommand closure must be provided"
    }

    @Test
    def void "DataSetbuilder must be immutable"(){
        def dataSetBuilder = getDataSetBuilder(BuildedClass, {})
        assert dataSetBuilder.setName() != dataSetBuilder
    }

    @Test
    def void "DataSetbuilder buildAndDoOnSuccess requesty must execute insertCommand"(){
        def inserted = false
        getDataSetBuilder(BuildedClass, {inserted = true}).buildAndDoOnSuccess({})
        assert inserted : "Builder method should have called insertCommand."
    }

    @Test
    def void "DataSetbuilder buildAndDo requesty must execute insertCommand"(){
        def inserted = false
        getDataSetBuilder(BuildedClass, {inserted = true}).buildAndDo({}, {})
        assert inserted : "Builder method should have called insertCommand."
    }

    @Test
    def void "DataSetbuilder buildAndDoOnFailure requesty must execute insertCommand"(){
        def inserted = false
        getDataSetBuilder(BuildedClass, {inserted = true}).buildAndDoOnFailure({})
        assert inserted : "Builder method should have called insertCommand."
    }

    @Test
    def void "DataSetbuilder build requesty must execute insertCommand"(){
        def inserted = false
        getDataSetBuilder(BuildedClass, {inserted = true}).build()
        assert inserted : "Builder method should have called insertCommand."
    }

    @Test
    def void "InsertCommand should be called upon success"(){
        def inserted = false
        getDataSetBuilder(BuildedClass, {inserted = true}).build()
        assert inserted : "InsertCommand was not called when the build process succeeded."
    }

    @Test
    def void "InsertCommand should not be called upon failure"(){
        def inserted = false
        getDataSetBuilder(BuildedClass, {inserted = true}).setError("Error").build()
        assert !inserted : "InsertCommand was called when the build process failed."
    }



    def getDataSetBuilder(aClass, insertCommand){
        return new DataSetBuilder(aClass, insertCommand)
    }

    public static class BuildedClass {

        public void setError(String error){
            issueError(this, [:], error)
        }

    }

}
