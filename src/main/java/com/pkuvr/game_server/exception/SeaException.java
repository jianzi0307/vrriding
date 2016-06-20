package com.pkuvr.game_server.exception;

import com.google.protobuf.InvalidProtocolBufferException;
import com.pkuvr.game_server.constant.SeaErrorCode;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import java.io.IOException;


public class SeaException extends Exception {
    private static final Logger log = Logger.getLogger(SeaException.class);
    private static final long serialVersionUID = 1L;
    private SeaErrorCode seaErrorCode = SeaErrorCode.OK;

    public SeaException(SeaErrorCode seaErrorCode) {
        this.seaErrorCode = seaErrorCode;
    }

    public SeaException(SeaErrorCode seaErrorCode, String message) {
        super(message);
        this.seaErrorCode = seaErrorCode;
    }

    public SeaException(SeaErrorCode seaErrorCode, Throwable cause) {
        super(cause);
        this.seaErrorCode = seaErrorCode;
    }

    public SeaException(SeaErrorCode seaErrorCode, String message, Throwable cause) {
        super(message, cause);
        this.seaErrorCode = seaErrorCode;
    }

    private static SeaErrorCode searchErrorCode(Exception e) {
        if (e instanceof InvalidProtocolBufferException) {
            return SeaErrorCode.ERROR_PARAM;
        } else if (e instanceof RuntimeException) {
            return SeaErrorCode.ERROR_UNKNOW;
        } else if (e instanceof NoSuchRoleException) {
            return SeaErrorCode.NO_SUCH_ROLE;
        } else if (e instanceof JsonParseException) {
            return SeaErrorCode.ERROR_UNKNOW;
        } else if (e instanceof JsonMappingException) {
            return SeaErrorCode.ERROR_UNKNOW;
        } else if (e instanceof IOException) {
            return SeaErrorCode.ERROR_UNKNOW;
        } else if (e instanceof PvpMatchFailedException) {
            return SeaErrorCode.PVP_MATCH_FAILED;
        } else if (e instanceof PlayerTacticsNotExistException) {
            return SeaErrorCode.PVP_TACTICS_NOT_EXIST;
        } else if (e instanceof PvpOtherNotOnlineException) {
            return SeaErrorCode.PVP_OTHER_NOT_ONLINE;
        } else if (e instanceof PvpMatchOverTimesException) {
            return SeaErrorCode.PVP_MATCH_OVER_TIMES;
        } else if (e instanceof PvpNpcMatchOverTimesException) {
            return SeaErrorCode.PVP_NPC_MATCH_OVER_TIMES;
        }

        return SeaErrorCode.ERROR_PARAM;
    }

    /**
     * tzz added for logout Exception and return error code to Client
     *
     * @param e
     * @param roleId
     * @return error code for client (ProtocolConfig)
     */
    public static int logoutException(Exception e, int roleId) {
        SeaErrorCode errorCode;
        if (e instanceof SeaException) {
            errorCode = ((SeaException) e).getSeaErrorCode();
        } else {
            errorCode = searchErrorCode(e);
        }

        if (errorCode.isWriteLog())
            log.error(errorCode.getDesc() + ";RoleId[" + roleId + "] 发现错误：ErrorCode-" + errorCode, e);
        else
            log.error(errorCode.getDesc() + ";RoleId[" + roleId + "] 发现错误：ErrorCode-" + errorCode);

        return errorCode.getErrorCode();
    }

    public SeaErrorCode getSeaErrorCode() {
        return seaErrorCode;
    }

    public int getErrorCode() {
        return seaErrorCode.getErrorCode();
    }

    public String getDesc() {
        return seaErrorCode.getDesc();
    }
}
