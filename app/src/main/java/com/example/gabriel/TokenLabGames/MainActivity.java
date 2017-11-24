package com.example.gabriel.TokenLabGames;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        AssetManager assets = this.getAssets();

        List<Game> games = JsonParse.readGamesFromFile(this.getAssets(), "games.json");

        ArrayList<String> nomes = new ArrayList<>();
        for (Game game : games) {
            nomes.add(game.toString());
        }

        GameListAdapter gameListAdapter = new GameListAdapter(this, games);

        // ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
        //                                               nomes);

        // ListView listaNomes = (ListView) findViewById(R.id.lista_nomes);

        // listaNomes.setAdapter(adapter);


        ExpandableListView listaGames = (ExpandableListView) findViewById(R.id.gamesListView);
        listaGames.setAdapter(gameListAdapter);
    }
}
