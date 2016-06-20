package com.pkuvr.game_server.exception;

/**
 * 玩家好友不在线
 *
 * @author ZY
 */
public class BuildingResourceOverLimitException extends Exception {
    private static final long serialVersionUID = 1L;

    public BuildingResourceOverLimitException() {
    }

    public BuildingResourceOverLimitException(String message) {
        super(message);
    }

    public BuildingResourceOverLimitException(Throwable cause) {
        super(cause);
    }

    public BuildingResourceOverLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}