package com.example.marveljavafx;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {
    public static void main(String[] args) throws Exception {
        final String publicKey = "12ce7984267a5279cf3565d0e27eead2";  // clé publique
        final String privateKey = "02dd3fdfec668d485cdb9814b3da1b5191197bd8";  // clé privée
        String timestamp = String.valueOf(System.currentTimeMillis());

        // Génère le hash pour l'authentification
        String hash = "";

        // Crée l'URL avec les paramètres d'authentification
        String requestUrl = "https://gateway.marvel.com:443/v1/public/comics?apikey=" + publicKey
                + "&ts=" + timestamp + "&hash=" + hash;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();

            // Parse et affiche les résultats JSON
            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray results = jsonResponse.getJSONObject("data").getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject comic = results.getJSONObject(i);
                String title = comic.getString("title");
                System.out.println("Comic Title: " + title);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
