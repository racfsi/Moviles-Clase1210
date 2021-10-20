package com.app.clase1210.service;

import com.app.clase1210.model.ModelPokemon;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicioPokemon {

    //METODOS CRUD


    @GET("pokemon")
    Call<ModelPokemon> getPokemones();

}
