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

public class YugiohAPI {
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
