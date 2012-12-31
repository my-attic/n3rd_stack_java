package models;

import org.codehaus.jackson.annotate.*;

//@JsonWriteNullProperties(false)
//@JsonIgnoreProperties({"id", "revision"})
public class Human {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_rev")
    private String revision;

    private String firstName;
    private String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Human () {
    }

    public Human (String firstName,String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

