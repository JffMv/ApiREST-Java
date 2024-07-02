package co.edu.ing.escuela.proofparcial;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Concurrent extends Thread {
    private String apiUrl;

    public Concurrent(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Response from API: " + response.toString());
            } else {
                System.out.println("GET request not worked. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String url = "http://ec2-54-166-147-53.compute-1.amazonaws.com:8080/status?nameCompany=IBM&time=TIME_SERIES_MONTHLY";

        for (int i = 0; i<10; i++) {
            Concurrent thread = new Concurrent(url);
            thread.start();
        }
    }
}
