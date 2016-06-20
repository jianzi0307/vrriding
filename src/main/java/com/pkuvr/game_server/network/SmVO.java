package com.pkuvr.game_server.network;

import com.pkuvr.game_server.constant.SmOpCode;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SmVO {
    SmOpCode opCode();
}
