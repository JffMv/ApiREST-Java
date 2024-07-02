package co.edu.ing.escuela.proofparcial;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * The FormController class is a REST controller that handles HTTP GET requests for retrieving
 * the status of a company based on the specified time and company name parameters.
 */
@RestController
public class FormController {
    private HashMap<String, String> cache = new HashMap<>();
    private String value;

    /**
     * Handles HTTP GET requests to the /status endpoint. Returns the status of the specified company
     * for the given time period. Caches the results to improve performance on subsequent requests.
     *
     * @param nameCompany the name of the company (default is "IBM").
     * @param time        the time period for the status (default is "TIME_SERIES_MONTHLY").
     * @return the status of the company as a JSON string.
     * @throws IOException if an I/O error occurs during the consultation.
     */
    @RequestMapping(
            value = "/status",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public String status(@RequestParam(value = "nameCompany", defaultValue = "IBM") String nameCompany,
                         @RequestParam(value = "time", defaultValue = "TIME_SERIES_MONTHLY") String time) throws IOException {
        String key = nameCompany + time;
        if (cache.containsKey(key)) {
            System.out.println("si esta en cache");
            return cache.get(key);
        } else {
            Conector httpConnectionExample = new HttpConnectionAlphaadvantage();
            String consultAPI = httpConnectionExample.consult(time, nameCompany);
            System.out.println(consultAPI);
            cache.put(key, consultAPI);
            return String.format(consultAPI);
        }
    }
}
