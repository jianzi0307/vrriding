package com.pkuvr.game_server.exception;

/**
 * 玩家好友已经存在
 *
 * @author ZY
 */
public class FriendAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public FriendAlreadyExistsException() {
    }

    public FriendAlreadyExistsException(String message) {
        super(message);
    }

    public FriendAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public FriendAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}