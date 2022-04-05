package com.gb.controller;
import com.gb.entity.Game;
import com.gb.exception.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gb.service.GameService;
import com.gb.types.Choice;


@RestController
@RequestMapping("/v1/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Game> start(
            @RequestParam("firstPlayerName") String playerOneName,
            @RequestParam("newGame") Boolean newGame)  {

        Game game = gameService.start(playerOneName,newGame);
        if(playerOneName!=null) {

            if (game != null)
                return new ResponseEntity<>(game,HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(
            @PathVariable("gameId") int id) {

        try {
            Game game = gameService.getGame(id);
            return new ResponseEntity<>(game, HttpStatus.OK);
        }
        catch (GameNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{gameId}")
    public Game choose(
            @PathVariable("gameId") int id,
            @RequestParam("choice") Choice playerOneChoice) {
        return gameService.choose(id, playerOneChoice);
    }

    @PostMapping("/{gameId}")
    public Game save(@PathVariable("gameId") int id){
            return gameService.save(id);
    }

}

