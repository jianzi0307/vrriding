package com.pkuvr.game_server.exception;

/**
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 */
public class PvpNpcMatchOverTimesException extends Exception {
    private static final long serialVersionUID = 1L;

    public PvpNpcMatchOverTimesException() {
    }

    public PvpNpcMatchOverTimesException(String message) {
        super(message);
    }

    public PvpNpcMatchOverTimesException(Throwable cause) {
        super(cause);
    }

    public PvpNpcMatchOverTimesException(String message, Throwable cause) {
        super(message, cause);
    }
}
