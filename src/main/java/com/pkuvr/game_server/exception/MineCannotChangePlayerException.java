package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class MineCannotChangePlayerException extends Exception {
    private static final long serialVersionUID = 1L;

    public MineCannotChangePlayerException() {
    }

    public MineCannotChangePlayerException(String message) {
        super(message);
    }

    public MineCannotChangePlayerException(Throwable cause) {
        super(cause);
    }

    public MineCannotChangePlayerException(String message, Throwable cause) {
        super(message, cause);
    }
}