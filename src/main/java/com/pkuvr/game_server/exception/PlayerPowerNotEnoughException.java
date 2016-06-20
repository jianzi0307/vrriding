package com.pkuvr.game_server.exception;

/**
 * <p>类说明:商品id错误或不存在</p>
 * <p>创建人及时间：	宋士龙 2012-8-2</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 */
public class PlayerPowerNotEnoughException extends Exception {
    private static final long serialVersionUID = 1L;

    public PlayerPowerNotEnoughException() {
    }

    public PlayerPowerNotEnoughException(String message) {
        super(message);
    }

    public PlayerPowerNotEnoughException(Throwable cause) {
        super(cause);
    }

    public PlayerPowerNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }
}
