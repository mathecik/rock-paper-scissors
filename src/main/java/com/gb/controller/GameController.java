package com.gb.controller;

import com.gb.entity.Game;
import com.gb.exception.GameNotFoundException;
import com.gb.service.GameService;
import com.gb.types.Choice;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Game> start(
            @RequestParam("firstPlayerName")  @NotNull String playerOneName,
            @RequestParam("newGame") Boolean newGame) {
        Game game = gameService.start(playerOneName, newGame);
        if (game != null)
            return new ResponseEntity<>(game, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(
            @PathVariable("gameId") int id) {
        try {
            Game game = gameService.getGame(id);
            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (GameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<String> play(
            @PathVariable("gameId") int id,
            @RequestParam("choice") @NotNull Choice playerOneChoice) {
        return new ResponseEntity<>(gameService.play(id, playerOneChoice), HttpStatus.OK);
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<Game> save(@PathVariable("gameId") int id) {
        return new ResponseEntity<>(gameService.save(id), HttpStatus.OK);
    }

}

