package couchdb;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;

public class CouchDB {

    public static CouchDbConnector getDb(String dbName, String url) {
        HttpClient httpClient = null;
        try {
            httpClient = new StdHttpClient.Builder()
                    .url(url)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector db = new StdCouchDbConnector(dbName, dbInstance);

        db.createDatabaseIfNotExists();
        return db;
    }
}


