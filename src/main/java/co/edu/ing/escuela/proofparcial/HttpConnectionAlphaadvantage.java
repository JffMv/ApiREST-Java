package co.edu.ing.escuela.proofparcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * The HttpConnectionAlphaadvantage class implements the Conector interface and is used to make HTTP GET requests
 * to the Alpha Vantage API to retrieve data for a specified company and time period.
 */
public class HttpConnectionAlphaadvantage implements Conector {
    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * This is the main method which makes use of the consult method.
     *
     * @param args Unused.
     * @throws IOException if an I/O error occurs.
     */
    public void main(String[] args) throws IOException {
        // Not used in this implementation
    }

    /**
     * Consults data from the Alpha Vantage API based on the specified time and company parameters.
     *
     * @param time    the time parameter for the API request.
     * @param company the company parameter for the API request.
     * @return a JSON string representing the API response.
     * @throws IOException if an I/O error occurs during the API request.
     */
    @Override
    public String consult(String time, String company) throws IOException {
        String url = "https://www.alphavantage.co/query?function=" + time + "&symbol=" + company + "&apikey=demo";

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());

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
