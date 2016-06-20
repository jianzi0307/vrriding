package com.pkuvr.game_server.exception;

/**
 * 好友人数超过上限50人
 *
 * @author SHACS
 */
public class TooManyFriendsException extends Exception {
    private static final long serialVersionUID = 1L;

    public TooManyFriendsException() {
    }

    public TooManyFriendsException(String message) {
        super(message);
    }

    public TooManyFriendsException(Throwable cause) {
        super(cause);
    }

    public TooManyFriendsException(String message, Throwable cause) {
        super(message, cause);
    }
}
