package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class MineInfoErrorException extends Exception {
    private static final long serialVersionUID = 1L;

    public MineInfoErrorException() {
    }

    public MineInfoErrorException(String message) {
        super(message);
    }

    public MineInfoErrorException(Throwable cause) {
        super(cause);
    }

    public MineInfoErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}