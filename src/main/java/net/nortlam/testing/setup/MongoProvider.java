package net.nortlam.testing.setup;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import org.bson.Document;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MongoProvider {

    private static final Logger LOG = Logger.getLogger(MongoProvider.class.getName());
    
    private MongoClient client;
    public static final String DATABASE = "people";
    public static final String COLLECTION = "persons";
    
    @PostConstruct
    private void init() {
        LOG.log(Level.INFO, ">>> init() CONNECTING TO MONGO");
//        client = new MongoClient("second-data", 27017);
        client = new MongoClient(new MongoClientURI("mongodb://mauricio:maltron@microservice-data:27017/sampledb"));
        
        try {
            MongoCollection<Document> collection = client.getDatabase(DATABASE).getCollection(COLLECTION);
            LOG.log(Level.INFO, ">>> Search list of Indexes");
            boolean alreadyExists = false;
            for(Document document: collection.listIndexes()) {
                alreadyExists = true; break;
            }
            
            if(!alreadyExists) {
                LOG.log(Level.INFO, ">>> Creating Indexes for the First Time");
                collection.createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));
            }
            
            try {
                Document document = new Document("email", "maltron")
                        .append("firstName", "Mauricio").append("lastName", "Leal");
                LOG.log(Level.INFO, ">>> Inserting #1:{0}", document);
                collection.insertOne(document);
                LOG.log(Level.INFO, ">>> Inserting #2:{0}", document);
                collection.insertOne(document);
                
            } catch(MongoWriteException ex) {
                LOG.log(Level.SEVERE, "### MONGO WRITE EXCEPTION:{0}", ex.getMessage());
            } catch(MongoWriteConcernException ex) {
                LOG.log(Level.SEVERE, "### MONGO WRITE CONCERN EXCEPTION:{0}", ex.getMessage());
            } catch(MongoException ex) {
                LOG.log(Level.SEVERE, "### MONGO EXCEPTION:{0}", ex.getMessage());
            }
            
                
            LOG.log(Level.INFO, ">>> Number of Documents:{0}", collection.count());
            
//            collection.createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));
            
        } catch(IllegalArgumentException ex) {
            LOG.log(Level.SEVERE, "### Unable to load either Database:{0} or Collection:{1}",
                    new Object[] {DATABASE, COLLECTION});
        }
        
        
    }
    
    @Lock(LockType.READ)
    public MongoClient getClient() {
        return client;
    }
    
}
