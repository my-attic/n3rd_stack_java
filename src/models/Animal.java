package models;

import org.codehaus.jackson.annotate.*;

//@JsonWriteNullProperties(false)
//@JsonIgnoreProperties({"id", "revision"})
public class Animal {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_rev")
    private String revision;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal () {
    }

    public Animal (String name) {
        this.name = name;
    }

}

