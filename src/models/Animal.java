package models;

import org.codehaus.jackson.annotate.*;

//@JsonWriteNullProperties(false)
//@JsonIgnoreProperties({"id", "revision"})
public class Animal {

    @JsonProperty("_id")
    public String id;

    @JsonProperty("_rev")
    public String revision;

    public String name;

    public Animal(String name) {
        this.name = name;
    }

    public Animal () {
    }
}
