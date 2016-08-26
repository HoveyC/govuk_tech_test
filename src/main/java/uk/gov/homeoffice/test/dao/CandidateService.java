package uk.gov.homeoffice.test.dao;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import uk.gov.homeoffice.test.pojo.Candidate;

/**
 *
 * @author chrishovey
 */
public interface CandidateService {

    public static CandidateService createService(Vertx vertx, JsonObject config) {
        return new CandidateServiceImpl(vertx, config);
    }
    void getCandidates(Handler<AsyncResult<ArrayList<Candidate>>> handler);
    
}
