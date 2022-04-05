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
        Player firstPlayer;
        try {
            firstPlayer = playerDAO.findById(playerOneName).orElseThrow();
        } catch (NoSuchElementException ex) {
            firstPlayer = new Player(playerOneName);
            playerDAO.save(firstPlayer);
        }

        Game game;
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
        return gameDAO.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

    @Override
    public String play(int id, Choice playerOneChoice) {
        Game game = gameDAO.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        String noti = evaluateBeater(game, playerOneChoice);
        gameDAO.save(game);
        return noti;
    }

    private String evaluateBeater(Game game, Choice playerOneChoice) {
        Choice computerChoice = Choice.chooseRandom();
        String beatNotification = "It is a DRAW!";
        if (playerOneChoice != computerChoice) {
            if (playerOneChoice.beats(computerChoice)) {
                game.setFirstPlayerScore(game.getFirstPlayerScore() + 1);
                beatNotification =game.getFirstPlayer().getPlayerName()+" wins!!";
            } else if(computerChoice.beats(playerOneChoice)){
                game.setSecondPlayerScore(game.getSecondPlayerScore() + 1);
                beatNotification =game.getSecondPlayer().getPlayerName()+" wins!!";
            }
        }
        game.setRoundCount(game.getRoundCount() + 1);
        return beatNotification;
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
