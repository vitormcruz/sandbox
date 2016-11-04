package sandbox.payroll.external.config

import sandbox.smartfactory.SmartFactory


class TestConfig implements Config{

    private smartFactory = SmartFactory.instance()

    @Override
    void configure() {
        def globalConfiguration = smartFactory.configurationFor("**")
        globalConfiguration.put(DatabaseCleaner, new DatabaseCleaner())
    }

    @Override
    void tearDown() {

    }
}
