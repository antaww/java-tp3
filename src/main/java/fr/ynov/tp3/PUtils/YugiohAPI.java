package fr.ynov.tp3.PUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe YugiohAPI : récupération des informations sur les cartes Yu-Gi-Oh! à partir d'une API.
 * Cette classe permet de récupérer les informations sur les cartes Yu-Gi-Oh! à partir d'une API qui contient les informations sur les cartes telles que le nom, le niveau, l'attribut, les types, la référence, l'attaque et la défense.
 * Cette classe est utilisée afin de remplir le fichier cards.json qui contient les informations sur les cartes Yu-Gi-Oh!.
 * Elle utilise la librairie Gson pour parser les informations de l'API et les stocker dans une liste de JsonObject.
 * Elle utilise également les classes BufferedReader et InputStreamReader pour lire les informations de l'API et les stocker dans un StringBuilder.
 * Elle utilise également la classe HttpURLConnection pour ouvrir une connexion à l'API.
 * Elle utilise également les classes ArrayList et Collections pour stocker les informations des cartes et les mélanger aléatoirement.
 *
 * @see com.google.gson.Gson
 * @see com.google.gson.JsonObject
 * @see java.io.BufferedReader
 * @see java.io.InputStreamReader
 * @see java.net.HttpURLConnection
 * @see java.net.URL
 * @see java.util.ArrayList
 * @see java.util.Collections
 * @see java.util.List
 */
public class YugiohAPI {
    /**
     * Méthode main : récupère les informations sur les cartes Yu-Gi-Oh! à partir de l'API
     * Cette méthode utilise l'API https://db.ygoprodeck.com/api/v7/cardinfo.php avec la querie ?language=fr pour récupérer les informations sur les cartes Yu-Gi-Oh! telles que le nom, le niveau, l'attribut, les types, la référence, l'attaque et la défense.
     * Elle utilise également les librairies Gson et Java IO pour lire et parser les informations de l'API.
     * Elle utilise également les classes JsonObject et JsonArray pour stocker les informations des cartes.
     * Elle mélange ensuite aléatoirement les cartes récupérées et en sélectionne 500 pour écrire dans un fichier JSON.
     *
     * @param args Arguments de la méthode main
     * @throws IOException Exception levée en cas d'erreur lors de la lecture des informations de l'API
     * @see com.google.gson.Gson
     * @see com.google.gson.JsonObject
     * @see com.google.gson.JsonArray
     * @see java.io.BufferedReader
     * @see java.io.InputStreamReader
     * @see java.net.HttpURLConnection
     */
    public static void main(final String[] args) throws IOException {
        final var url = new URL("https://db.ygoprodeck.com/api/v7/cardinfo.php?language=fr");
        final var connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        final var in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        final var content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        final var gson = new Gson();
        final var json = gson.fromJson(content.toString(), JsonObject.class);
        final var data = json.getAsJsonArray("data");

        final List<JsonObject> dataList = new ArrayList<>();
        for (final var element : data) {
            dataList.add(element.getAsJsonObject());
        }
        Collections.shuffle(dataList);

        final var subList = dataList.subList(0, 500);
        final var jsonString = gson.toJson(subList);

        //  Uncomment to write to file
        //  var file = new File("src/main/resources/cards.json");
        //  var writer = new FileWriter(file, true);
        //  writer.append(jsonString);
        //  writer.close();
    }
}
