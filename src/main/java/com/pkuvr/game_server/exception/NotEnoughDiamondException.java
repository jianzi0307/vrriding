package com.pkuvr.game_server.exception;

/**
 * 钻石、黄金、石材、油料等 其中之一不足
 *
 * @author ZY
 */
public class NotEnoughDiamondException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotEnoughDiamondException() {
        super();
    }

    public NotEnoughDiamondException(String message) {
        super(message);
    }

    public NotEnoughDiamondException(Throwable cause) {
        super(cause);
    }

    public NotEnoughDiamondException(String message, Throwable cause) {
        super(message, cause);
    }
}
