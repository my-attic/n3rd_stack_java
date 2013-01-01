package repositories;

import java.util.*;

import models.*;
import org.ektorp.*;
import org.ektorp.support.*;
import org.k33g.helpers.CouchDB;

public class Tasks extends CouchDbRepositorySupport<Task> {

    private static CouchDbConnector tasksDB = CouchDB.getDb("tasksdb", "http://localhost:5984");
    public static Tasks repository = new Tasks(tasksDB);

    public Tasks(CouchDbConnector db) {
        super(Task.class, db);
        initStandardDesignDocument();
    }

}


