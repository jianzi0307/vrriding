package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class MineOwnerOnlineException extends Exception {
    private static final long serialVersionUID = 1L;

    public MineOwnerOnlineException() {
    }

    public MineOwnerOnlineException(String message) {
        super(message);
    }

    public MineOwnerOnlineException(Throwable cause) {
        super(cause);
    }

    public MineOwnerOnlineException(String message, Throwable cause) {
        super(message, cause);
    }
}