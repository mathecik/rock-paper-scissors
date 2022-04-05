package com.gb.dao;

import com.gb.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerDAO extends JpaRepository<Player, String> {

}