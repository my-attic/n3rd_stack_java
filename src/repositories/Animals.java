package repositories;

import models.*;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.k33g.helpers.CouchDB;

public class Animals extends CouchDbRepositorySupport<Animal> {

    private static CouchDbConnector animalsDB = CouchDB.getDb("animalsdb", "http://localhost:5984");
    public static Animals repository = new Animals(animalsDB);

    public Animals(CouchDbConnector db) {
        super(Animal.class, db);
    }
}



