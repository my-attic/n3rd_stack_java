package repositories;

import models.Human;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class HumanRepository extends CouchDbRepositorySupport<Human> {
    public HumanRepository(CouchDbConnector db) {
        super(Human.class, db);
    }
}


