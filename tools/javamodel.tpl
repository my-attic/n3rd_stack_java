package models;

import org.codehaus.jackson.annotate.*;

//@JsonWriteNullProperties(false)
//@JsonIgnoreProperties({"id", "revision"})
public class {{model_name}} {

    @JsonProperty("_id")
    public String id;

    @JsonProperty("_rev")
    public String revision;


    public {{model_name}} () {
    }
}
