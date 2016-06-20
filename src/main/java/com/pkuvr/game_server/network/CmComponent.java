package com.pkuvr.game_server.network;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CmComponent {
    CmOpCode opCode(); // opCode 值

    State state(); // 状态(连接,验证)

    String value() default ""; // bean 名字
}
