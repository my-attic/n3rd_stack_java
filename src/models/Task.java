package models;

import org.codehaus.jackson.annotate.*;

//@JsonWriteNullProperties(false)
//@JsonIgnoreProperties({"id", "revision"})
public class Task {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_rev")
    private String revision;

    private String label;
    private String who;
    private boolean done;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Task () {
    }

    public Task (String label,String who,boolean done) {
        this.label = label;
        this.who = who;
        this.done = done;
    }

}

