package co.edu.ing.escuela.proofparcial;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class FormController {
    private  HashMap<String,String> cache = new HashMap<>();
    private String value;
    @RequestMapping(
            value = "/status",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public String status(@RequestParam(value = "nameCompany", defaultValue = "IBM")String nameCompany, @RequestParam(value = "time", defaultValue = "TIME_SERIES_MONTHLY")String time) throws IOException {
        String key = nameCompany+time;
        if (cache.containsKey(key)){
         System.out.println("si esta en cache");
         return cache.get(key);
        } else{
         Conector HttpConnectionExample = new HttpConnectionAlphaadvantage();
         String consultAPI =  HttpConnectionExample.consult(time, nameCompany);
         System.out.printf(consultAPI);
         cache.put(key,consultAPI);
         return String.format(consultAPI);
        }
    }
}




