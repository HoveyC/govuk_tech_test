package uk.gov.homeoffice.test.pojo;

import io.vertx.core.json.JsonArray;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * <p>
 * This class is used to serialise and deserialise Json request objects using
 * Jackson library.
 * <p>
 * By performing the serialise and deserialise, I can ensure the format of the
 * json string the service receives and produces are correct.
 * <p>
 * @author chrishovey
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate extends JsonSerialisable {

    
    private int id;
    private String name;
    private String dob;
    private String experience;

    /**
     * This Constructor is for creating instances from a JsonArray object returned 
     * back from the database
     * 
     * @param args 
     */
    public Candidate(JsonArray args) {
        try {
          
            this.id = args.getInteger(0);
            this.name = (args.getString(1));
            this.dob = LocalDate.parse(args.getString(2)).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.experience = args.getString(3);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
