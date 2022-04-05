package com.gb.service;

import com.gb.dao.GameDAO;
import com.gb.dao.PlayerDAO;
import com.gb.entity.Game;
import com.gb.entity.Player;
import com.gb.exception.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gb.types.Choice;
import com.gb.util.Constant;

import java.util.NoSuchElementException;

@Service
public class GameServiceImpl implements GameService {

    private PlayerDAO playerDAO;
    private GameDAO gameDAO;

    @Autowired
    public GameServiceImpl(GameDAO gameDAO, PlayerDAO playerDAO) {
        this.gameDAO = gameDAO;
        this.playerDAO = playerDAO;
    }

    @Override
    public Game start(String playerOneName, Boolean newGame) {
        Player firstPlayer = null;
        try {
            firstPlayer = playerDAO.findById(playerOneName).orElseThrow();
        } catch (NoSuchElementException ex) {
            firstPlayer = new Player(playerOneName);
            playerDAO.save(firstPlayer);
        }

        Game game = null;
        if (newGame == null || !newGame) {
            if (firstPlayer.getSavedGameId() == null) {
                game = new Game();
                game.setFirstPlayer(firstPlayer);
                game.setSecondPlayer(Constant.SECOND_PLAYER);
                game.setSecondPlayerScore(0);
                game.setFirstPlayerScore(0);
                game.setRoundCount(0);
                game = gameDAO.save(game);
            } else {
                int id = firstPlayer.getSavedGameId();
                game = gameDAO.findById(id).orElseThrow(() -> new GameNotFoundException(id));
            }
        } else {
            firstPlayer.setSavedGameId(null);
            game = new Game();
            game.setFirstPlayer(firstPlayer);
            game.setSecondPlayer(Constant.SECOND_PLAYER);
            game.setSecondPlayerScore(0);
            game.setFirstPlayerScore(0);
            game.setRoundCount(0);
            game = gameDAO.save(game);
        }
        return game;
    }


    @Override
    public Game getGame(int id) {
        Game game = gameDAO.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        return game;
    }

    @Override
    public Game choose(int id, Choice playerOneChoice) {
        Game game = gameDAO.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        evaluateBeater(game, playerOneChoice);
        gameDAO.save(game);
        return game;
    }

    private void evaluateBeater(Game game, Choice playerOneChoice) {
        Choice computerChoice = Choice.chooseRandom();
        if (playerOneChoice != computerChoice) {
            if (playerOneChoice.beats(computerChoice)) {
                game.setFirstPlayerScore(game.getFirstPlayerScore() + 1);
            } else {
                game.setSecondPlayerScore(game.getSecondPlayerScore() + 1);
            }
        }
        game.setRoundCount(game.getRoundCount() + 1);
    }

    @Override
    public Game save(int id) {
        Game game = gameDAO.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        Player player = game.getFirstPlayer();
        player.setSavedGameId(id);
        playerDAO.save(player);
        return game;
    }

}
