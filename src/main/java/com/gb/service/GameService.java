package com.gb.service;

import com.gb.entity.Game;
import com.gb.types.Choice;

public interface GameService {

    Game start(String playerOneName, Boolean newGame);

    Game getGame(int id);

    String play(int id, Choice playerOneChoice);

    Game save(int id);

}
