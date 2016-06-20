package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class MineInstanceUnderAttackedException extends Exception {
    private static final long serialVersionUID = 1L;

    public MineInstanceUnderAttackedException() {
    }

    public MineInstanceUnderAttackedException(String message) {
        super(message);
    }

    public MineInstanceUnderAttackedException(Throwable cause) {
        super(cause);
    }

    public MineInstanceUnderAttackedException(String message, Throwable cause) {
        super(message, cause);
    }
}