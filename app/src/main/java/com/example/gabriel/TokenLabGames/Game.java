package com.example.gabriel.TokenLabGames;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

// Classe Game: armazena informacoes sobre jogo obtido a partir do arquivo JSON
public class Game {
    @SerializedName("id") private int id;
    @SerializedName("name") private String name;

    // Links para a imagem de capa e trailer do jogo
    @SerializedName("image") private String imgURL;
    @SerializedName("release_date") private String releaseDate;
    @SerializedName("trailer") private String trailerURL;

    // Plataformas nas quais o jogo esta disponivel
    @SerializedName("platforms") private ArrayList<String> platforms;

    // Construtor nao parametrizado
    public Game() {
        this.id = 0;
        this.name = "";
        this.imgURL = "";
        this.releaseDate = "";
        this.trailerURL = "";
        this.platforms = new ArrayList<>();
    }

    // Getters
    public int getID() { return this.id; }
    public String getName() { return this.name; }
    public String getImgURL() { return this.imgURL; }
    public String getReleaseDate() { return this.releaseDate; }
    public String getTrailerURL() { return this.trailerURL; }
    public ArrayList<String> getPlatforms() { return this.platforms; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setimgURL(String imgURL) { this.imgURL = imgURL; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public void setTrailerURL(String trailerURL) { this.trailerURL = trailerURL; }
    public void setPlatforms(ArrayList<String> platforms) {
        this.platforms = new ArrayList<>(platforms);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ");
        sb.append(this.getName());
        sb.append("\n");
        sb.append("URL imagem: ");
        sb.append(this.getImgURL());
        sb.append("\n");
        sb.append("Data de lançamento: ");
        sb.append(this.getReleaseDate());
        sb.append("\n");
        sb.append("URL trailer: ");
        sb.append(this.getTrailerURL());
        sb.append("\n");
        sb.append("Plataformas: ");
        sb.append(this.getPlatforms().toString());

        return sb.toString();
    }

}
