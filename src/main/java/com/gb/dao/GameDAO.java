package com.gb.dao;

import com.gb.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDAO extends JpaRepository<Game, Integer> {

}