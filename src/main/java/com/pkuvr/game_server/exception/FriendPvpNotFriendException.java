package com.pkuvr.game_server.exception;

/**
 * 玩家好友不在线
 *
 * @author ZY
 */
public class FriendPvpNotFriendException extends Exception {
    private static final long serialVersionUID = 1L;

    public FriendPvpNotFriendException() {
    }

    public FriendPvpNotFriendException(String message) {
        super(message);
    }

    public FriendPvpNotFriendException(Throwable cause) {
        super(cause);
    }

    public FriendPvpNotFriendException(String message, Throwable cause) {
        super(message, cause);
    }
}