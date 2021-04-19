package com.xylia.domain.payments.assignment.controller;

import com.xylia.domain.payments.assignment.model.Box;
import com.xylia.domain.payments.assignment.model.Pokemon;
import com.xylia.domain.payments.assignment.service.PokemonStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PokemonController {

    @Autowired
    PokemonStorageService pokemonStorageService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/boxes")
    public ResponseEntity<Box> createBox(@RequestBody Box box) {
        return ResponseEntity
                .ok(pokemonStorageService.createBox(box));
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/boxes/{id}/pokemons")
    public ResponseEntity<List<Pokemon>> createPokemonsInsideBox(@PathVariable("id") int boxId,
                                                                 @RequestBody List<Pokemon> pokemons) {

        return ResponseEntity
                .ok(pokemonStorageService.addPokemonsToBox(boxId, pokemons));
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/boxes/{id}/pokemons")
    public ResponseEntity<List<Pokemon>> getPokemonsInsideBox(@PathVariable("id") int boxId) {

        return ResponseEntity
                .ok(pokemonStorageService.getPokemonsInsideBox(boxId));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/party/pokemons")
    public ResponseEntity<List<Pokemon>> createPokemonsInsideParty(@RequestBody List<Pokemon> pokemons) {
        return ResponseEntity
                .ok(pokemonStorageService.addPokemonsToParty(pokemons));
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/party/pokemons")
    public ResponseEntity<List<Pokemon>> getPokemonsInsideParty() {

        return ResponseEntity
                .ok(pokemonStorageService.getPokemonsInsideParty());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
