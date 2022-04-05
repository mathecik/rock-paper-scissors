package com.gb.exception;

public class GameNotFoundException extends RuntimeException {
	public GameNotFoundException(int id) {
		super("Could not find game " + id);
	}
}