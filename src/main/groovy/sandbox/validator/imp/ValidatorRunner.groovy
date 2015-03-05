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
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.imp.MethodTeacher
import sandbox.magritte.testGenerator.junit.FrameworkMetaMethod
import sandbox.validator.ParentValidatorRunner
import sandbox.validator.Validation

import static org.junit.internal.runners.rules.RuleFieldValidator.RULE_METHOD_VALIDATOR
import static org.junit.internal.runners.rules.RuleFieldValidator.RULE_VALIDATOR

//TODO maybe I should extend ParentRunner.....
//TODO Explain that it should not be used with @RunWith annotation...
class ValidatorRunner extends ParentRunner<FrameworkMethod> implements ParentValidatorRunner{

    def private static methodTeacher = new MethodTeacher()
    
    private Object objectUnderValidation

    /**
     * Constructs a new ValidationRunner for an object under validation, passed by parameter.
     *
     * This constructor makes it spam an error if anyone tries to execute this with an @RunWith annotation.
     */
    ValidatorRunner(Object objectUnderValidation) throws InitializationError {
        super(objectUnderValidation.class)
        this.objectUnderValidation = objectUnderValidation
    }
    
    @Override
    protected List getChildren() {
        return computeTestMethods();
    }

    protected List<FrameworkMethod> computeTestMethods() {
        def knownMethods = new ArrayList<>(getTestClass().getAnnotatedMethods(Validation))
        def teachedMethods = methodTeacher.teach(getTestClass().getJavaClass(), getGeneratedMethods())
        teachedMethods.each {knownMethods.add(new FrameworkMetaMethod(it))}
        return knownMethods
    }

    private Collection<GeneratedMethod> getGeneratedMethods() {
        return MagritteDescriptionModelBuilder.forObject(objectUnderValidation).asMethodGenerator()
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
        /* Wanted the same method without the verification for zero computed test method, i.e., I don't wanna the
        validation to fail just because there are no validation methods, actually, I wanna exactly the contrary.*/
        validatePublicVoidNoArgMethods(After.class, false, errors);
        validatePublicVoidNoArgMethods(Before.class, false, errors);
        validatePublicVoidNoArgMethods(Validation.class, false, errors);
    }

    @Override
    protected Description describeChild(FrameworkMethod method) {
        return Description.createTestDescription(getTestClass().getJavaClass(),
                method.getName(), method.getAnnotations());
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
        
        
        //TODO I wanna to do this using rules, but for now I will comment out since I am not using yet. Befores and after will not work until I fix that
//        statement = possiblyExpectingExceptions(method, test, statement);
//        statement = withPotentialTimeout(method, test, statement);
//        statement = withBefores(method, test, statement);
//        statement = withAfters(method, test, statement);
//        statement = withRules(method, test, statement);
        return statement;
    }
    
    protected Object createTest() throws Exception {
        return objectUnderValidation
    }


}
