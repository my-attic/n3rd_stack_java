package models;

import couchdb.Model;
import repositories.{{model_name}}s;

public class {{model_name}} extends Model {

    public static {{model_name}}s repository = new {{model_name}}s();

    {{#properties}}
    private {{type}} {{private_name}};
    {{/properties}}

    /*you can use only public fields if you want*/

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

