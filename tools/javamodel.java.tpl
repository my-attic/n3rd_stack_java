package models;

import org.codehaus.jackson.annotate.*;

//@JsonWriteNullProperties(false)
@JsonIgnoreProperties({"id", "revision"})
public class {{model_name}} {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_rev")
    private String revision;

    {{#properties}}
    private {{type}} {{private_name}};
    {{/properties}}

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

    {{#properties}}
    public {{type}} get{{name}}() {
        return {{private_name}};
    }

    public void set{{name}}({{type}} {{private_name}}) {
        this.{{private_name}} = {{private_name}};
    }
    {{/properties}}

    public {{model_name}} () {
    }

    public {{model_name}} ({{constructor_args}}) {
    {{#properties}}
        this.{{private_name}} = {{private_name}};
    {{/properties}}
    }

}

