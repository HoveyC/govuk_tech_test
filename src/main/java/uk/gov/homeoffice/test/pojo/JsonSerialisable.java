package uk.gov.homeoffice.test.pojo;

import io.vertx.core.json.Json;

/**
 *
 * This class is extened by all the json POJO's to provide a readable
 * way to serialising the POJO.
 * 
 * @author chrishovey
 *
 */
public abstract class JsonSerialisable {
    
    public String toJsonString() {
        return Json.encode(this);
    }
}
