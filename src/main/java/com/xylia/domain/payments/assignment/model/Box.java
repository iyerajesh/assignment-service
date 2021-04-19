package com.xylia.domain.payments.assignment.model;


import com.google.common.collect.Lists;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Box {

    private int boxId;
    @Builder.Default
    private List<Pokemon> pokeCollection = new ArrayList<Pokemon>(30);
    
    public void addPokemonsToBox(List<Pokemon> pokemons) {

        if (pokeCollection.size() == 30)
            throw new IllegalArgumentException("Each box cannot hold more than 30 pokemons!");

        if (pokemons.size() + pokeCollection.size() > 30)
            throw new IllegalArgumentException("Each box cannot hold more than 30 pokemons!");

        pokeCollection.addAll(pokemons);
    }

    public boolean exists(Pokemon poke) {

        List<Pokemon> pokeExists = pokeCollection.stream()
                .filter(pokemon -> pokemon.getPokeId() == poke.getPokeId()).collect(Collectors.toList());

        if (pokeExists.size() > 0)
            return true;

        return false;
    }
}
