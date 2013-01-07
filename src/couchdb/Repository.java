package couchdb;

import org.ektorp.CouchDbConnector;
import org.ektorp.Revision;
import org.ektorp.support.CouchDbRepositorySupport;

import java.util.List;

public class Repository<T> extends CouchDbRepositorySupport<T> {

    private static CouchDbConnector db;


    public Repository(Class<T> type, CouchDbConnector db) {
        super(type, db);
        this.db = db;
        initStandardDesignDocument();
    }

    public List<Revision> getDocRevisions(String id) {
        return db.getRevisions(id);
    }
}
