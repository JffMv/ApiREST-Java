package co.edu.ing.escuela.proofparcial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The Concurrent class extends Thread and is used to make HTTP GET requests to a given API URL concurrently.
 */
public class Concurrent extends Thread {
    private String apiUrl;

    /**
     * Constructs a Concurrent object with the specified API URL.
     *
     * @param apiUrl the URL to which the GET request is sent.
     */
    public Concurrent(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * The run method is executed when the thread is started.
     * It makes an HTTP GET request to the specified API URL and prints the response.
     */
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

    /**
     * The main method to test the Concurrent class.
     * It creates and starts 10 threads, each making a GET request to the specified URL.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        String url = "http://ec2-54-166-147-53.compute-1.amazonaws.com:8080/status?nameCompany=IBM&time=TIME_SERIES_MONTHLY";
        Concurrent[] threads = new Concurrent[10];

        // Create and start threads
        for (int i = 0; i < 10; i++) {
            threads[i] = new Concurrent(url);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All threads have finished.");
    }
}
