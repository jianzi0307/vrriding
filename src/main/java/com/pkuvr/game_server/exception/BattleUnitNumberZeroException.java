package com.pkuvr.game_server.exception;

/**
 * 玩家好友不在线
 *
 * @author ZY
 */
public class BattleUnitNumberZeroException extends Exception {
    private static final long serialVersionUID = 1L;

    public BattleUnitNumberZeroException() {
    }

    public BattleUnitNumberZeroException(String message) {
        super(message);
    }

    public BattleUnitNumberZeroException(Throwable cause) {
        super(cause);
    }

    public BattleUnitNumberZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}