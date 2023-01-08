package fr.ynov.tp3.PUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YugiohAPI {
    public static void main(String[] args) throws IOException {
        var url = new URL("https://db.ygoprodeck.com/api/v7/cardinfo.php?language=fr");
        var connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        var in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        var content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        var gson = new Gson();
        var json = gson.fromJson(content.toString(), JsonObject.class);
        var data = json.getAsJsonArray("data");

        List<JsonObject> dataList = new ArrayList<>();
        for (var element : data) {
            dataList.add(element.getAsJsonObject());
        }
        Collections.shuffle(dataList);

        var subList = dataList.subList(0,500);
        var jsonString = gson.toJson(subList);

        var file = new File("src/main/resources/cards.json");
        var writer = new FileWriter(file, true);
        writer.append(jsonString);
        writer.close();
    }
}
