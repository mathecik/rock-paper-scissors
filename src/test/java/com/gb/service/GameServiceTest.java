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
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameServiceTest {
    private GameDAO gameDAO;
    private PlayerDAO playerDAO;
    private GameService gameService;
    private Game game;
    private Player player;

    @Before
    public void setup() {
        gameDAO = mock(GameDAO.class);
        playerDAO = mock(PlayerDAO.class);
        gameService = new GameServiceImpl(gameDAO,playerDAO);
        player = mock(Player.class);
        player.setPlayerName("DEFNE");
        game = mock(Game.class);
        game.setGameId(1);
        game.setFirstPlayer(player);
        game.setSecondPlayer(Constant.SECOND_PLAYER);
        game.setSecondPlayerScore(0);
        game.setFirstPlayerScore(0);
        game.setRoundCount(0);
    }

    @Test
    public void testGetGame() {
        when(gameDAO.findById(1)).thenReturn(Optional.ofNullable(game));
        Game testResult = gameService.getGame(1);
        assertEquals(testResult.getGameId(), game.getGameId());
        assertEquals(testResult.getFirstPlayer(), game.getFirstPlayer());
        assertEquals(testResult.getSecondPlayer(), game.getSecondPlayer());
        assertEquals(testResult.getFirstPlayerScore(), game.getFirstPlayerScore());
        assertEquals(testResult.getSecondPlayerScore(), game.getSecondPlayerScore());
        assertEquals(testResult.getRoundCount(), game.getRoundCount());
    }

    @Test
    public void testSave() {
        Player givenPlayer = new Player();
        givenPlayer.setSavedGameId(1);
        when(gameDAO.findById(any())).thenReturn(Optional.ofNullable(game));
        gameService.save(1);
        assertEquals(Optional.ofNullable(givenPlayer.getSavedGameId()), game.getGameId());
    }

    @Test
    public void testPlayFirstPlayerWins() {
        Game givenGame = new Game();
        Player givenPlayer = new Player("Cici");
        givenGame.setFirstPlayer(givenPlayer);
        givenGame.setFirstPlayerScore(10);
        when(Choice.chooseRandom()).thenReturn(Choice.ROCK);
        String not = gameService.play(1, Choice.PAPER);
        assertTrue(not.startsWith(givenGame.getFirstPlayer().getPlayerName()));
    }

    @Test
    public void testPlaySecondPlayerWins() {
        Game givenGame = new Game();
        Player givenPlayer = new Player("Kiki");
        givenGame.setSecondPlayer(givenPlayer);
        when(Choice.chooseRandom()).thenReturn(Choice.ROCK);
        String not = gameService.play(1, Choice.SCISSORS);
        assertTrue(not.startsWith(givenGame.getSecondPlayer().getPlayerName()));
    }
}