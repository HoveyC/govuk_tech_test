package uk.gov.homeoffice.test.dao;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.sql.SQLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import uk.gov.homeoffice.test.pojo.Candidate;

/**
 *
 * @author chrishovey
 */
public class CandidateServiceImpl implements CandidateService {
    
    private AsyncSQLClient postgreSQLClient;

    public CandidateServiceImpl(Vertx vertx, JsonObject postgreSQLClientConfig) {
        this.postgreSQLClient = PostgreSQLClient.createShared(vertx, postgreSQLClientConfig);;
    }
    
    @Override
    public void getCandidates(Handler<AsyncResult<ArrayList<Candidate>>> handler) {
        ArrayList<Candidate> candidates = new ArrayList<>();
        postgreSQLClient.getConnection(res -> {
            SQLConnection connection = res.result();
            connection.query("SELECT * FROM candidate", result -> {
                if (result.succeeded()) {
                    Iterator<JsonArray> i = result.result().getResults().iterator();
                    while (i.hasNext()) {
                        candidates.add(new Candidate(i.next()));
                    }
                    handler.handle(Future.succeededFuture(candidates));//done
                    postgreSQLClient.close();
                } else {
                    handler.handle(Future.failedFuture(result.cause()));
                    postgreSQLClient.close();
                }
            });
     
        });
    }

}
