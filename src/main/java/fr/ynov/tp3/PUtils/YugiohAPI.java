package fr.ynov.tp3.PUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YugiohAPI {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://db.ygoprodeck.com/api/v7/cardinfo.php?language=fr");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        Gson gson = new Gson();
        JsonObject json = gson.fromJson(content.toString(), JsonObject.class);
        JsonArray data = json.getAsJsonArray("data");

        List<JsonObject> dataList = new ArrayList<>();
        for (JsonElement element : data) {
            dataList.add(element.getAsJsonObject());
        }
        Collections.shuffle(dataList);

        List<JsonObject> subList = dataList.subList(0,500);
        String jsonString = gson.toJson(subList);

        File file = new File("src/main/resources/cards.json");
        FileWriter writer = new FileWriter(file, true);
        writer.append(jsonString);
        writer.close();
    }
}
