package com.pkuvr.game_server.exception;

/**
 * 钻石、黄金、石材、油料等 其中之一不足
 *
 * @author ZY
 */
public class NotEnoughResourceException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotEnoughResourceException() {
        super();
    }

    public NotEnoughResourceException(String message) {
        super(message);
    }

    public NotEnoughResourceException(Throwable cause) {
        super(cause);
    }

    public NotEnoughResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
