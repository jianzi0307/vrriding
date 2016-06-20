package com.pkuvr.game_server.exception;

/**
 * 钻石、黄金、石材、油料等 其中之一不足
 *
 * @author ZY
 */
public class NotEnoughIronException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotEnoughIronException() {
        super();
    }

    public NotEnoughIronException(String message) {
        super(message);
    }

    public NotEnoughIronException(Throwable cause) {
        super(cause);
    }

    public NotEnoughIronException(String message, Throwable cause) {
        super(message, cause);
    }
}
