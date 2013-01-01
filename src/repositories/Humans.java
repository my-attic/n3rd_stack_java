package repositories;

import java.util.*;

import models.*;
import org.ektorp.*;
import org.ektorp.support.*;
import org.k33g.helpers.CouchDB;

public class Humans extends CouchDbRepositorySupport<Human> {

    private static CouchDbConnector humansDB = CouchDB.getDb("humansdb", "http://localhost:5984");
    public static Humans repository = new Humans(humansDB);

    public Humans(CouchDbConnector db) {
        super(Human.class, db);
        initStandardDesignDocument();
    }

}


