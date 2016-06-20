package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class MineChangePlayerNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public MineChangePlayerNotFoundException() {
    }

    public MineChangePlayerNotFoundException(String message) {
        super(message);
    }

    public MineChangePlayerNotFoundException(Throwable cause) {
        super(cause);
    }

    public MineChangePlayerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}