package com.xylia.domain.payments.assignment.service;

import com.google.common.collect.Lists;
import com.xylia.domain.payments.assignment.model.Box;
import com.xylia.domain.payments.assignment.model.Party;
import com.xylia.domain.payments.assignment.model.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonStorageService {

    private List<Box> boxStorage = Lists.newArrayList();
    private Party partyStorage = new Party();

    public final Box createBox(Box box) {
        boxStorage.add(box);
        return box;
    }

    public final List<Pokemon> addPokemonsToBox(int boxId, List<Pokemon> pokemons) throws IllegalArgumentException {

        List<Pokemon> existsList = pokemons.stream()
                .filter(poke -> checkIfPokemonExistsInBoxStorage(poke)).collect(Collectors.toList());

        if (existsList.size() > 0)
            throw new IllegalArgumentException("One of the Pokemons to be added is already in storage!");

        Box box = getBox(boxId);
        box.addPokemonsToBox(pokemons);

        return pokemons;
    }

    public final List<Pokemon> addPokemonsToParty(List<Pokemon> pokemons) throws IllegalArgumentException {

        List<Pokemon> existsList = pokemons.stream()
                .filter(poke -> checkIfPokemonExistsInPartyStorage(poke)).collect(Collectors.toList());

        if (existsList.size() > 0)
            throw new IllegalArgumentException("One of the Pokemons to be added is already in storage!");

        partyStorage.addPokemonsToParty(pokemons);

        return pokemons;
    }

    public final List<Pokemon> getPokemonsInsideBox(int boxId) {
        Box box = getBox(boxId);
        return box.getPokeCollection();
    }

    public final List<Pokemon> getPokemonsInsideParty() {
        return partyStorage.getPokeCollection();
    }

    private final Box getBox(int boxId) {
        Optional<Box> optionalBox = boxStorage.stream().filter(box -> box.getBoxId() == boxId)
                .findFirst();

        return optionalBox.get();
    }

    private boolean checkIfPokemonExistsInBoxStorage(Pokemon pokemon) {

        List<Box> boxExists = boxStorage.stream().filter(box -> box.exists(pokemon)).collect(Collectors.toList());

        if (boxExists.size() > 0)
            return true;

        return false;
    }

    private boolean checkIfPokemonExistsInPartyStorage(Pokemon pokemon) {

        return partyStorage.exists(pokemon);
    }


}
