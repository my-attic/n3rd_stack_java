package repositories;

import java.util.*;

import models.*;
import org.ektorp.*;
import org.ektorp.support.*;
import org.k33g.helpers.CouchDB;

public class Animals extends CouchDbRepositorySupport<Animal> {

    private static CouchDbConnector animalsDB = CouchDB.getDb("animalsdb", "http://localhost:5984");
    public static Animals repository = new Animals(animalsDB);

    public Animals(CouchDbConnector db) {
        super(Animal.class, db);
        initStandardDesignDocument();
    }

    @GenerateView
    public List<Animal> findByName(String name) {
        return queryView("by_name", name);
    }

}


