package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class PlayerTacticsNotExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public PlayerTacticsNotExistException() {
    }

    public PlayerTacticsNotExistException(String message) {
        super(message);
    }

    public PlayerTacticsNotExistException(Throwable cause) {
        super(cause);
    }

    public PlayerTacticsNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}