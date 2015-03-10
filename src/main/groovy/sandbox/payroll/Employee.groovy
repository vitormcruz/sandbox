package sandbox.payroll
import org.apache.commons.lang.StringUtils
import sandbox.magritte.description.DescriptionContainer
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.validator.Validation
import sandbox.validator.imp.ValidatorTrait

import static sandbox.magritte.description.builder.DescriptionFactory.New

class Employee implements ValidatorTrait {

    def Long id
    def String name
    def String address
    def String email

    @DescriptionModelDefinition
    public myDescription(){

        /*
           TODO new Ideas
            New ideas for description API for user.

        For java this would seam odd, because you would have a Class that has a static description() method, that
        could be named Description already
        return [description().string("name").maxSize(50).label("employee.name"),
                description().string("address").maxSize(100).label("employee.address"),
                description().string("address").maxSize(100).label("employee.address")]


        This would clash with Description Interface
        return [Description.string("name").maxSize(50).label("employee.name"),
                Description.string("address").maxSize(100).label("employee.address"),
                Description.string("address").maxSize(100).label("employee.address")]

        This way I loose the definition that those things are descriptions. For constraints this is not a big deal,
        but for other things, like description for test, workflow or etc, I don`t know.
        return [string("name").maxSize(50).label("employee.name"),
                string("address").maxSize(100).label("employee.address"),
                string("address").maxSize(100).label("employee.address")]


        return [description(for("name"), ).string("name").maxSize(50).label("employee.name"),
                description().string("address").maxSize(100).label("employee.address"),
                description().string("address").maxSize(100).label("employee.address")]



        return [
            "name".isA(StringDescription).maxSize(50).label("employee.name"),
            "address".isA(StringDescription).maxSize(200).label("employee.address"),
            "email".isA(StringDescription).maxSize(100).label("employee.email"),
        ]

        in Java:

        return [
            isA("name", StringDescription).maxSize(50).label("employee.name"),
            isA("address", StringDescription).maxSize(200).label("employee.address"),
            isA("email", StringDescription).maxSize(100).label("employee.email"),
        ]


        */

        return New(DescriptionContainer).addAll(New(StringDescription).accessor("name").maxSize(50).label("employee.name").beRequired(),
                                                New(StringDescription).accessor("address").maxSize(200).label("employee.address").beRequired(),
                                                New(StringDescription).accessor("email").maxSize(100).label("employee.email").beRequired())
    }
}
