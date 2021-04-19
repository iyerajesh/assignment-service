package com.xylia.domain.payments.assignment.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Party {

    @Builder.Default
    private List<Pokemon> pokeCollection = new ArrayList<Pokemon>(6);

    public void addPokemonsToParty(List<Pokemon> pokemons) {

        if (pokeCollection.size() == 6)
            throw new IllegalArgumentException("Each party cannot hold more than 6 pokemons!");

        if (pokemons.size() + pokeCollection.size() > 6)
            throw new IllegalArgumentException("Each party cannot hold more than 30 pokemons!");

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
