package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class MineAlreadyUnlockedException extends Exception {
    private static final long serialVersionUID = 1L;

    public MineAlreadyUnlockedException() {
    }

    public MineAlreadyUnlockedException(String message) {
        super(message);
    }

    public MineAlreadyUnlockedException(Throwable cause) {
        super(cause);
    }

    public MineAlreadyUnlockedException(String message, Throwable cause) {
        super(message, cause);
    }
}