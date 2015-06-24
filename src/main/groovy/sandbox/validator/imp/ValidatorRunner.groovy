package sandbox.validator.imp
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.internal.runners.model.ReflectiveCallable
import org.junit.internal.runners.statements.Fail
import org.junit.internal.runners.statements.InvokeMethod
import org.junit.runner.Description
import org.junit.runner.notification.RunNotifier
import org.junit.runners.ParentRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.InitializationError
import org.junit.runners.model.Statement
import org.junit.runners.model.TestClass
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.imp.MethodTeacher
import sandbox.magritte.testGenerator.junit.FrameworkMetaMethod
import sandbox.validator.ParentValidatorRunner
import sandbox.validator.Validation

import static org.junit.internal.runners.rules.RuleMemberValidator.RULE_METHOD_VALIDATOR
import static org.junit.internal.runners.rules.RuleMemberValidator.RULE_VALIDATOR
//TODO maybe I should extend ParentRunnerMoreAbstract.....
//TODO Explain that it should not be used with @RunWith annotation...
class ValidatorRunner extends ParentRunner<FrameworkMethod> implements ParentValidatorRunner{

    def private static methodTeacher = new MethodTeacher()
    
    private Object subjectOfValidation

    /**
     * Constructs a new ValidationRunner for an object under validation, passed by parameter.
     *
     * This constructor makes it spam an error if anyone tries to execute this with an @RunWith annotation.
     */
    ValidatorRunner(Object subjectOfValidation) throws InitializationError {
        super(subjectOfValidation.getClass())
        this.subjectOfValidation = subjectOfValidation
    }

    @Override
    protected TestClass createTestClass(Class<?> classOfValidationSubject) {
        return new ValidationClass(classOfValidationSubject)
    }

    @Override
    protected List getChildren() {
        return computeTestMethods();
    }

    protected List<FrameworkMethod> computeTestMethods() {
        def knownMethods = new ArrayList<>(getTestClass().getAnnotatedMethods(Validation))
        //TODO Make teacher teach only if not already taught, i.e, only once
        def teachedMethods = methodTeacher.teach(getTestClass().getJavaClass(), getGeneratedMethods())
        //TODO Remove .contains("validationFor"). validationFor_someMethod should only be used by invokeMethod before someMethod execution, but I don't know how to separate those methods from those who should be executed by ValidationRunner.
        teachedMethods.each {
            if(!it.getName().contains("validationFor")) {
                knownMethods.add(new FrameworkMetaMethod(it))
            }
        }
        return knownMethods
    }

    private Collection<GeneratedMethod> getGeneratedMethods() {
        return MagritteDescriptionModelBuilder.forObject(subjectOfValidation).asMethodGenerator()
                                                                               .getGeneratedMethods()
    }
    
    //TODO use validation framework! How cool would be that?
    @Override
    protected void collectInitializationErrors(List<Throwable> errors) {
        super.collectInitializationErrors(errors);

        //TODO validate that the class has a default constructor (Maybe this validations exits in some API)
        validateInstanceMethods(errors);
        validateFields(errors);
        validateMethods(errors);
    }
    
    protected void validateFields(List<Throwable> errors) {
        RULE_VALIDATOR.validate(getTestClass(), errors);
    }

    private void validateMethods(List<Throwable> errors) {
        RULE_METHOD_VALIDATOR.validate(getTestClass(), errors);
    }

    protected void validateInstanceMethods(List<Throwable> errors) {
        validatePublicVoidNoArgMethods(After.class, false, errors);
        validatePublicVoidNoArgMethods(Before.class, false, errors);
        validatePublicVoidNoArgMethods(Validation.class, false, errors);
    }

       @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        Description description = describeChild(method);
        if (method.getAnnotation(Ignore.class) != null) {
            notifier.fireTestIgnored(description);
        } else {
            runLeaf(methodBlock(method), description, notifier);
        }
    }

    @Override
    protected Description describeChild(FrameworkMethod method) {
        return Description.createTestDescription(getTestClass().getJavaClass(),
                method.getName(), method.getAnnotations());
    }

    //TODO copied from JUNIT. See if it will remain like this
    protected Statement methodBlock(FrameworkMethod method) {
        Object test;
        try {
            test = new ReflectiveCallable() {
                @Override
                protected Object runReflectiveCall() throws Throwable {
                    return createTest();
                }
            }.run();
        } catch (Throwable e) {
            return new Fail(e);
        }

        Statement statement = new InvokeMethod(method, test);
        
        
        //TODO I wanna to do this using rules, but for now I will comment out since I am not using yet. Befores and Afters will not work until I fix that
//        statement = possiblyExpectingExceptions(method, test, statement);
//        statement = withPotentialTimeout(method, test, statement);
//        statement = withBefores(method, test, statement);
//        statement = withAfters(method, test, statement);
//        statement = withRules(method, test, statement);
        return statement;
    }
    
    protected Object createTest() throws Exception {
        return subjectOfValidation
    }

}
