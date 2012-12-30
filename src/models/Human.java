package models;

import org.codehaus.jackson.annotate.*;

/*
http://backbonejs.org/#Model-idAttribute
 */

//@JsonWriteNullProperties(false)
//@JsonIgnoreProperties({"id", "revision"})
public class Human {

    @JsonProperty("_id")
    public String id;

    @JsonProperty("_rev")
    public String revision;

    public String firstName;
    public String lastName;


    public Human() {
    }

    public Human(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Human{" + "firstName=" + firstName +
                ", lastName=" + lastName + ", id=" + id + '}';
    }
}
