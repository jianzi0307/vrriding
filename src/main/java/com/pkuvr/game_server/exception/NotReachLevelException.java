package com.pkuvr.game_server.exception;

/**
 * 舰队长名不允许为空
 *
 * @author SHACS
 */
public class NotReachLevelException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotReachLevelException() {
    }

    public NotReachLevelException(String message) {
        super(message);
    }

    public NotReachLevelException(Throwable cause) {
        super(cause);
    }

    public NotReachLevelException(String message, Throwable cause) {
        super(message, cause);
    }
}
