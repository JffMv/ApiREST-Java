package co.edu.ing.escuela.proofparcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class HttpConnectionAlphaadvantage implements Conector {
    private static final String USER_AGENT = "Mozilla/5.0";

    public void main(String[] args) throws IOException {

    }
    public String consult(String time, String company) throws IOException {
        String url = "https://www.alphavantage.co/query?function=" +time+ "&symbol="+company+"&apikey=demo";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the full JSON response (optional)
            System.out.println(response.toString());

            // Parse JSON and extract "Production"
            JSONObject jsonResponse = new JSONObject(response.toString());
            String production = jsonResponse.optString("Production", "N/A");
            System.out.println("Production: " + production);
            System.out.println("GET DONE");
            return response.toString();
        } else {
            System.out.println("GET request not worked");
            System.out.println("GET DONE");
            return "d";
        }
    }
}
