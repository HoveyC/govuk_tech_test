package uk.gov.homeoffice.test.rs;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import uk.gov.homeoffice.test.pojo.JsonError;
import io.vertx.ext.web.handler.StaticHandler;
import uk.gov.homeoffice.test.dao.CandidateService;

public class ApplicantsRestService extends AbstractVerticle {

    /**
     *
     * This method is called by vertx on startup and assigns request handler to
     * /candidates Any other requests will be sent back a 501 not
     * implemented error.
     * 
     * the database configuration is initialised and a database client is created 
     * with the config ready for connections to be made 
     */
    @Override
    public void start() {
        JsonObject postgreSQLClientConfig = new JsonObject()
                .put("host", "localhost")
                .put("port", 5432)
                .put("username", "postgres")
                .put("password", "")
                .put("database", "homeoffice");

        CandidateService candidateService = CandidateService.createService(vertx, postgreSQLClientConfig);
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.get("/candidates").handler(routingContext -> {
            getCandidates(candidateService, routingContext);
        });
        router.route("/*").handler(StaticHandler.create());//try it.
        //Catch all other requests not served
        router.route().handler(routingContext -> {
            routingContext.response()
                    .setStatusCode(501)
                    .setStatusMessage("Not Implemented")
                    .putHeader("content-type", "text/plain; charset=utf-8")
                    .end(JsonError.builder()
                            .errorCode(501)
                            .errorMessage("Not Implemented")
                            .build()
                            .toJsonString());

        });
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

    /**
     * 
     * This method calls the data access layer for the candidate data and creates a 
     * response for the web font in a Json format
     * 
     * @param candidateService
     * @param routingContext 
     * 
     * T 
     */
    
    private void getCandidates(CandidateService candidateService, RoutingContext routingContext) {
        candidateService.getCandidates(r -> {
            if (r.succeeded()) {
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encode(r.result()));
            } else {
                routingContext.response()
                        .setStatusCode(500)
                        .putHeader("content-type", "text/plain; charset=utf-8")
                        .end("Internal Server Error");
            }
        });
    }
}
