package repositories;

import models.Human;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.k33g.helpers.CouchDB;

public class Humans extends CouchDbRepositorySupport<Human> {

    private static CouchDbConnector humansDB = CouchDB.getDb("humansdb", "http://localhost:5984");
    public static Humans repository = new Humans(humansDB);

    public Humans(CouchDbConnector db) {
        super(Human.class, db);
    }
}


