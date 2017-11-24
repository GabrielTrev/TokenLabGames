package com.example.gabriel.TokenLabGames;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Classe contendo funcoes auxiliares para realizar parsing do arquivo json
public class JsonParse {
    // Parse do arquivo Json contido no projeto
    public static List<Game> readGamesFromFile(AssetManager manager, String path) {
        List<Game> games = new ArrayList<>();

        try {
            InputStream stream = manager.open(path);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();

            String jsonString = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            JsonObject jasonObj = gson.fromJson(jsonString, JsonObject.class);
            JsonArray gamesObj = jasonObj.getAsJsonArray("games");

            Game[] gamesArr = gson.fromJson(gamesObj, Game[].class);
            games = new ArrayList<>(Arrays.asList(gamesArr));

            return games;

        } catch (Exception e) {
            e.printStackTrace();
            return games;
        }
    }
}
