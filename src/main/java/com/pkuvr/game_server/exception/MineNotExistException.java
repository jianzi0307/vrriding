package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class MineNotExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public MineNotExistException() {
    }

    public MineNotExistException(String message) {
        super(message);
    }

    public MineNotExistException(Throwable cause) {
        super(cause);
    }

    public MineNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}