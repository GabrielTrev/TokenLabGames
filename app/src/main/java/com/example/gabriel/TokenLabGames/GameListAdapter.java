package com.example.gabriel.TokenLabGames;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

// Adapter para a lista expandivel de games
public class GameListAdapter extends BaseExpandableListAdapter {

    // Constantes para os tipos de view
    private static final int TYPE_GAME_IMAGE = 0;
    private static final int TYPE_GAME_INFO = 1;

    // Numero de tipos
    private static final int TYPE_COUNT = 2;
    // Numero de membros do objeto game
    private static final int ATTR_COUNT = 4;

    // Lista de games a serem mostrados
    private List<Game> games = new ArrayList<>();
    // Contexto de execucao do programa
    private Context context;

    private boolean trailerVisible = false;

    // Construtor parametrizado
    public GameListAdapter(Context context, List<Game> games) {
        this.games = games;
        this.context = context;
    }

    // Setters e getters
    public void setGames(List<Game> games) { this.games = games; }
    public void setContext(Context context) { this.context = context; }
    public List<Game> getGames() { return this.games; }
    public Context getContext() { return this.context; }

    @Override
    public int getGroupCount() {
        // Retorna o numero de objetos da lista
        return this.games.size();
    }

    @Override
    public int getChildrenCount(int i) {
        // Numero de atributos para cada jogo = 4
        return ATTR_COUNT;
    }

    @Override
    public Object getGroup(int i) {
        // Retorna o objeto presente na posicao i
        return games.get(i);
    }

    @Override
    public Object getChild(int gameNum, int option) {

        if (gameNum > games.size() - 1) { return null; }
        Game game = games.get(gameNum);

        /* Membro 0: Nome do jogo
         * Membro 1: URL da imagem
         * Membro 2: Data de lancamento
         * Membro 3: URL do trailer
         * Membro 4: Plataformas */
        switch(option) {
            case 0:
                return game.getName();
            case 1:
                return game.getImgURL();
            case 2:
                return game.getReleaseDate();
            case 3:
                return game.getPlatforms();
            case 4:
                return game.getTrailerURL();
            default:
                return null;
        }
    }

    @Override
    public long getGroupId(int pos) {
        // Como o id nao esta sendo utilizado, apenas retorna a posicao
        return pos;
    }

    @Override
    public long getChildId(int gameNum, int option) {
        // Como o id nao esta sendo utilizado, apenas retorna a posicao
        return option;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int gameNum, boolean expanded, View view, ViewGroup viewGroup) {
        if (gameNum > this.games.size() - 1) { return null; }

        // Cria as views principais para os nomes
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                                                           Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.layout_game_option, null);
        }

        TextView tvGameName = (TextView) view.findViewById(R.id.tvGameName);
        tvGameName.setText(this.games.get(gameNum).getName());

        return view;
    }

    @Override
    public int getChildTypeCount() {
        // Retorna o numero de tipos de view para cada subitem
        return TYPE_COUNT;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        // Retorna tipo de view para um subitem
        if (childPosition == 0) { return TYPE_GAME_IMAGE; }
        // else if (childPosition == 3) { return this.TYPE_GAME_TRAILER; }
        return TYPE_GAME_INFO;
    }

    @Override
    public View getChildView(int gameNum, int option, boolean expanded, View view, ViewGroup viewGroup) {
        if (gameNum > this.games.size() - 1) { return null; }

        int viewType = this.getChildType(gameNum, option);

        // Cria as views secundarias para as informacoes adicionais
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                                                           Context.LAYOUT_INFLATER_SERVICE);

            switch(viewType) {
                // Carregamento da imagem
                case TYPE_GAME_IMAGE:
                    view = layoutInflater.inflate(R.layout.layout_game_image, null);
                    break;

                // Carregamento das informacoes adicionais
                case TYPE_GAME_INFO:
                    view = layoutInflater.inflate(R.layout.layout_game_info, null);
                    break;
            }

        }

        String nomeAtributo, valorAtributo;

        /* Membro 0: URL da imagem
         * Membro 1: Data de lancamento
         * Membro 2: Plataformas
         * Membro 3: URL do trailer */
        switch(option) {
            case 0:
                nomeAtributo = "Imagem";
                valorAtributo = games.get(gameNum).getImgURL();
                break;
            case 1:
                nomeAtributo = "Data de Lancamento";
                valorAtributo = games.get(gameNum).getReleaseDate();
                break;
            case 2:
                nomeAtributo = "Plataformas";
                valorAtributo = games.get(gameNum).getPlatforms().toString();
                break;
            case 3:
                nomeAtributo = "Trailer";
                valorAtributo = games.get(gameNum).getTrailerURL();
                break;
            default:
                nomeAtributo = "";
                valorAtributo = "";
        }

        if (option == 0) {
            ImageView ivGameImage = (ImageView) view.findViewById(R.id.ivGameImage);
            ivGameImage.setImageResource(android.R.color.transparent);
            Picasso.with(this.context).load(valorAtributo).into(ivGameImage);
        }
        else {
            TextView tvGameAttrName = (TextView) view.findViewById(R.id.tvGameAttrName);
            TextView tvGameAttrValue = (TextView) view.findViewById(R.id.tvGameAttrValue);

            tvGameAttrName.setText(nomeAtributo);
            tvGameAttrValue.setText(valorAtributo);

            if (option == 3) {
                tvGameAttrValue.setClickable(true);
                tvGameAttrValue.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href='" + valorAtributo + "'>Link para Video</a>";
                tvGameAttrValue.setText(Html.fromHtml(text));
            }

        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
