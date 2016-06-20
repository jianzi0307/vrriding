package com.pkuvr.game_server.exception;

/**
 * 钻石、黄金、石材、油料等 其中之一不足
 *
 * @author ZY
 */
public class NotEnoughOilException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotEnoughOilException() {
        super();
    }

    public NotEnoughOilException(String message) {
        super(message);
    }

    public NotEnoughOilException(Throwable cause) {
        super(cause);
    }

    public NotEnoughOilException(String message, Throwable cause) {
        super(message, cause);
    }
}
