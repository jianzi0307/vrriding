package com.pkuvr.game_server.exception;

/**
 * 钻石、黄金、石材、油料等 其中之一不足
 *
 * @author ZY
 */
public class NotEnoughWealthException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotEnoughWealthException() {
        super();
    }

    public NotEnoughWealthException(String message) {
        super(message);
    }

    public NotEnoughWealthException(Throwable cause) {
        super(cause);
    }

    public NotEnoughWealthException(String message, Throwable cause) {
        super(message, cause);
    }
}
