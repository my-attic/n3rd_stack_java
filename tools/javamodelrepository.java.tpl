package repositories;

import java.util.*;

import models.*;
import org.ektorp.*;
import org.ektorp.support.*;
import org.k33g.helpers.CouchDB;

public class {{model_name}}s extends CouchDbRepositorySupport<{{model_name}}> {

    private static CouchDbConnector {{_model_name}}sDB = CouchDB.getDb("{{_model_name}}sdb", "http://localhost:5984");
    public static {{model_name}}s repository = new {{model_name}}s({{_model_name}}sDB);

    public {{model_name}}s(CouchDbConnector db) {
        super({{model_name}}.class, db);
        initStandardDesignDocument();
    }

    {{#properties}}
    @GenerateView
    public List<{{model_name}}> findBy{{name}}({{type}} {{private_name}}) {
        return queryView("by_{{private_name}}", {{private_name}});
    }

    {{/properties}}
}


