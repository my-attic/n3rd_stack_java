package repositories;

import java.util.*;

import couchdb.Repository;
import models.*;
import org.ektorp.support.*;
import couchdb.CouchDB;

public class {{model_name}}s extends Repository<{{model_name}}> {

    public {{model_name}}s() {
        super({{model_name}}.class, CouchDB.getDb("{{_model_name}}sdb", "http://localhost:5984"));
    }

    {{#properties}}
    @GenerateView
    public List<{{model_name}}> findBy{{name}}({{type}} {{private_name}}) {
        return queryView("by_{{private_name}}", {{private_name}});
    }

    {{/properties}}
}

