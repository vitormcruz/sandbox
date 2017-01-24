package com.vmc.sandbox.payroll

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
/**
 * I make the target object identifiable by an id attribute that defaults to a UUID string generated randomly (type 4).
 * I also provide equals and hashCode implementation tested with a general code that I recommend you to reuse if you
 * need to provide a different equals or hashcode method. If you provide an object with a good equals and hash code
 * implementation you might not need to override my equals and hash code implementation. If you do take care and don't
 * forget to implement the canEqual method. (See http://www.artima.com/lejava/articles/equality.html for details)
 *
 * Beeing identifiable is a good property in an OO system since it facilitates a number of things. If the object don't
 * have a natural identifier, the UIID is provided. If otherwise remember to override my getter implementation because
 * it is there I provide the UUID string. I make that because as a trait I cannot define a constructor and I didn't
 * want to define it upon declaration to allow reflection magic.
 */
trait IdentifiableTrait {

    private id

    public getId(){
        if(id == null){
            id = UUID.randomUUID().toString()
        }

        return this.@id;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode()
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) {
            return false
        }

        if(obj.is(this)) {
            return true
        }

        if(!(this.getClass().isInstance(obj))) {
            return false
        }

        if(!obj.canEqual(this)){
            return false
        }

        if(id == null || obj.getId() == null) {
            return super.equals(obj)
        }


        return new EqualsBuilder().append(getId(), obj.getId())
                                  .isEquals();
    }

    /**
     * Read item #4 of http://www.artima.com/lejava/articles/equality.html
     *
     * Define in the form of return other instanceof <MyClass>
     *
     */
    public abstract boolean canEqual(Object other);

}
