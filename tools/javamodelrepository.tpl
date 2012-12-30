package repositories;

import models.*;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.k33g.helpers.CouchDB;

public class {{model_name}}s extends CouchDbRepositorySupport<{{model_name}}> {

    private static CouchDbConnector {{_model_name}}sDB = CouchDB.getDb("{{_model_name}}sdb", "http://localhost:5984");
    public static {{model_name}}s repository = new {{model_name}}s({{_model_name}}sDB);

    public {{model_name}}s(CouchDbConnector db) {
        super({{model_name}}.class, db);
    }
}



