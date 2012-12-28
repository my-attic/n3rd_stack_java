package repositories;

import org.ektorp.CouchDbConnector;
import org.k33g.helpers.CouchDB;

public class Repositories {
    private static CouchDbConnector humanDB = CouchDB.getDb("humansdb", "http://localhost:5984");
    public static HumanRepository humanRepository = new HumanRepository(humanDB);
}
