package com.gb.service;

import com.gb.dao.GameDAO;
import com.gb.dao.PlayerDAO;
import com.gb.entity.Game;
import com.gb.entity.Player;
import org.junit.Before;
import org.junit.Test;
import com.gb.types.Choice;
import com.gb.util.Constant;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameServiceTest {
    private GameDAO gameDAO;

    private PlayerDAO playerDAO;

    private GameService gameServiceTest;

    private Game gameTest;

    private Player playerTest;

    @Before
    public void setup() {
        gameDAO = mock(GameDAO.class);
        playerDAO = mock(PlayerDAO.class);
        gameServiceTest = new GameServiceImpl(gameDAO,playerDAO);

        playerTest = mock(Player.class);
        playerTest.setPlayerName("DEFNE");
        gameTest = mock(Game.class);
        gameTest.setGameId(1);
        gameTest.setFirstPlayer(playerTest);
        gameTest.setSecondPlayer(Constant.SECOND_PLAYER);
        gameTest.setSecondPlayerScore(0);
        gameTest.setFirstPlayerScore(0);
        gameTest.setRoundCount(0);

    }


    @Test
    public void getGameTest() {

        when(gameDAO.findById(1)).thenReturn(Optional.ofNullable(gameTest));

        Game testResult = gameServiceTest.getGame(1);

        assertEquals(testResult.getGameId(),gameTest.getGameId());
        assertEquals(testResult.getFirstPlayer(),gameTest.getFirstPlayer());
        assertEquals(testResult.getSecondPlayer(),gameTest.getSecondPlayer());
        assertEquals(testResult.getFirstPlayerScore(),gameTest.getFirstPlayerScore());
        assertEquals(testResult.getSecondPlayerScore(),gameTest.getSecondPlayerScore());
        assertEquals(testResult.getRoundCount(),gameTest.getRoundCount());

    }

    @Test
    public void saveTest() {

        Player givenPlayer = new Player();
        givenPlayer.setSavedGameId(1);

        when(gameDAO.findById(any())).thenReturn(Optional.ofNullable(gameTest));
        gameServiceTest.save(1);

        assertEquals(Optional.ofNullable(givenPlayer.getSavedGameId()), gameTest.getGameId());

    }


    @Test
    public void chooseTest() {
        Game givenGame = new Game();
        givenGame.setFirstPlayerScore(10);
        when(Choice.chooseRandom()).thenReturn(Choice.ROCK);
        Game game = gameServiceTest.choose(1, Choice.PAPER);
        assertEquals(game.getFirstPlayerScore(),101);

    }

}