package sandbox.validator.imp

import org.junit.runners.model.FrameworkField
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.TestClass

import java.lang.annotation.Annotation

class ValidationClass extends TestClass{

    ValidationClass(classOfValidationSubject) {
        super(DummyClass.class)

        //FIXME Hack. See below
        /**
         * To remove that I would have to change JUnit implementation of ParentRunner, TestClass and a number of other
         * classes. Since This is yet an experience I change the constructor of TestCase here by reflection so that
         * validations classes can have more than one constructor.
         */
        def clazz = TestClass.class.getDeclaredField("clazz")
        def methodsForAnnotations = TestClass.class.getDeclaredField("methodsForAnnotations")
        def fieldsForAnnotations = TestClass.class.getDeclaredField("fieldsForAnnotations")
        clazz.setAccessible(true)
        methodsForAnnotations.setAccessible(true)
        fieldsForAnnotations.setAccessible(true)

        clazz.set(this, classOfValidationSubject)

        Map<Class<? extends Annotation>, List<FrameworkMethod>> newMethodsForAnnotations =
                new LinkedHashMap<Class<? extends Annotation>, List<FrameworkMethod>>();
        Map<Class<? extends Annotation>, List<FrameworkField>> newFieldsForAnnotations =
                new LinkedHashMap<Class<? extends Annotation>, List<FrameworkField>>();

        super.scanAnnotatedMembers(newMethodsForAnnotations, newFieldsForAnnotations);

        methodsForAnnotations.set(this, makeDeeplyUnmodifiable(newMethodsForAnnotations));
        fieldsForAnnotations.set(this, makeDeeplyUnmodifiable(newFieldsForAnnotations));
    }


    public static class DummyClass{

    }
}
