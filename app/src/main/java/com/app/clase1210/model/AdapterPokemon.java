package com.app.clase1210.model;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.clase1210.R;
import com.app.clase1210.myclass.Pokemon;

import java.util.List;

public class AdapterPokemon extends BaseAdapter {

    private List<Pokemon> pokemonList;
    Context context;

    public AdapterPokemon(List<Pokemon> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pokemonList.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemonList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        Pokemon pokemon = (Pokemon) getItem(position);
        converView = LayoutInflater.from(context).inflate(R.layout.element,null);

        if(converView != null){
            TextView name = converView.findViewById(R.id.tvName);
            TextView url = converView.findViewById(R.id.tvUrl);

            name.setText(pokemon.getName());
            url.setText(pokemon.getUrl());
        }else{
            Log.i(TAG, "Error en el adaptador: ");
        }

        return converView;
    }
}
