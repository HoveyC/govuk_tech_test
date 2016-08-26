package uk.gov.homeoffice.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents an error message when the json object cannot be parsed.
 * 
 * @author chrishovey
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonError extends JsonSerialisable {

    private int errorCode;
    private String errorMessage;

}
