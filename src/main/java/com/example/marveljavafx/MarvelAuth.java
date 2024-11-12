package com.example.marveljavafx;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MarvelAuth {

    private final String publicKey = "12ce7984267a5279cf3565d0e27eead2"; // Clé publique
    private final String privateKey = "02dd3fdfec668d485cdb9814b3da1b5191197bd8"; // Clé privée
    private final String timestamp = String.valueOf(System.currentTimeMillis()); // timestamp

    // Génère le hash pour l'authentification
    private String generateHash(String timestamp, String publicKey, String privateKey)  {
        String toHash = timestamp + privateKey + publicKey;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(toHash.getBytes());
            StringBuilder hash = new StringBuilder();
            for (byte b : hashBytes) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpURLConnection fetchApiResponse() {
        //On utilise la méthode de hachage en mettant nos trois variables prédéfinis
        String hash = generateHash(this.timestamp, this.publicKey, this.privateKey);

        // Crée l'URL avec les paramètres d'authentification
        String requestUrl = "https://gateway.marvel.com:443/v1/public/comics?apikey=" + this.publicKey
                + "&ts=" + this.timestamp + "&hash=" + hash;


        try {
            URL url = new URL(requestUrl); //Création d'une variable url à partir du string requestUrl
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Typage de l'url

            // Requete de type GET pour récupérer les données
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            return connection;
        } catch(IOException e){
            e.getMessage();
        }

        //connection impossible
        return null;
    }

}
