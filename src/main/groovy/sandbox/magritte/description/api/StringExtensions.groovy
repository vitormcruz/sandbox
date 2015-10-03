package sandbox.magritte.description.api

import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.StringDescription

import static sandbox.magritte.description.builder.DescriptionFactory.New

/**
 */
class StringExtensions {

    public static StringDescription isAString(String accessor){
        return New(StringDescription).accessor(accessor)
    }

    public static NumberDescription isANumber(String accessor){
        return New(NumberDescription).accessor(accessor)
    }

}
