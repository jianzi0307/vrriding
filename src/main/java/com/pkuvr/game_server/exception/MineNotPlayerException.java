package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class MineNotPlayerException extends Exception {
    private static final long serialVersionUID = 1L;

    public MineNotPlayerException() {
    }

    public MineNotPlayerException(String message) {
        super(message);
    }

    public MineNotPlayerException(Throwable cause) {
        super(cause);
    }

    public MineNotPlayerException(String message, Throwable cause) {
        super(message, cause);
    }
}