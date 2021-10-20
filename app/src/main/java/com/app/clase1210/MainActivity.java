    package com.app.clase1210;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.clase1210.model.AdapterPokemon;
import com.app.clase1210.model.ModelPokemon;
import com.app.clase1210.myclass.Pokemon;
import com.app.clase1210.service.ServicioPokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class MainActivity extends AppCompatActivity {

    private AdapterPokemon adapterPokemon;
    private Retrofit retrofit;
    private ListView listView;
    public static final String BASE_URL = "https://pokeapi.co/api/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciador();
        showPokemos();
    }
    //SIEMPRE QUE CONSUMAMOS UN API DEBEMOS USAR ESTE MISMO ESQUEMA
    private void showPokemos(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServicioPokemon poke = retrofit.create(ServicioPokemon.class);
        Call<ModelPokemon> pokemonCall= poke.getPokemones();
        pokemonCall.enqueue(new Callback<ModelPokemon>() {
            @Override
            public void onResponse(Call<ModelPokemon> call, Response<ModelPokemon> response) {
                if(response.isSuccessful()){
                    ModelPokemon modelPokemon = response.body();
                    ArrayList<Pokemon> lista = modelPokemon.getResults();
                    adapterPokemon = new AdapterPokemon(lista,getApplicationContext());
                    listView.setAdapter(adapterPokemon);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getApplicationContext(), ""+listView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Error en respuesta"+response.errorBody(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ModelPokemon> call, Throwable t) {
                Log.i(null, "Hubo un error : "+t);
            }
        });

    }

    private void iniciador(){
        listView = findViewById(R.id.listPok);
    }
}