import com.ee.dynamicmongoquery.MongoQuery;
import com.ee.dynamicmongoquery.MongoQueryParser;
import com.mongodb.BasicDBList;
import com.mongodb.DB;
import com.mongodb.Mongo;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by lanxiang on 16/8/22.
 */
public class TestMongoShell {

    private DB db;

    private void initMongoDB() throws Exception {
        Mongo mongo = new Mongo("127.0.0.1", 27017);
        db = mongo.getDB("myNewDatabase");
    }

    @Test
    public void testFind() throws Exception {
        initMongoDB();
        String query = "db.myCollection.find({'type':'database'})";
        MongoQuery mongoQuery = new MongoQueryParser().parse(query, new HashMap());
        BasicDBList results = mongoQuery.execute(db);
        for (Object o : results) {
            System.out.println(o.toString());
        }
    }
}
