package com.pkuvr.game_server.network;

import com.pkuvr.commons.network.netty.AbstractPipeLineFactory;
import com.pkuvr.commons.network.netty.coder.PacketFrameDecoder;
import com.pkuvr.commons.network.netty.coder.PacketFrameEncoder;
import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import org.jboss.netty.handler.logging.LoggingHandler;
import org.jboss.netty.logging.InternalLogLevel;

/**
 * 为了给每一个Channel通道创建一个新的Handler实例，我们需要实现一个ChannelPipelineFactory管道工厂
 *
 * @author SHACS
 */
public class GameServerPipeLineFactory extends AbstractPipeLineFactory {

    static OrderedMemoryAwareThreadPoolExecutor e = new OrderedMemoryAwareThreadPoolExecutor(10240, 0, 0);
    static ExecutionHandler executionHandler = new ExecutionHandler(e);

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = Channels.pipeline();

        pipeline.addLast("logger", new LoggingHandler(Logger.class, InternalLogLevel.DEBUG, true));
        pipeline.addLast("framedecoder", new PacketFrameDecoder());
        pipeline.addLast("frameencoder", new PacketFrameEncoder(2));
        //pipeline.addLast("executor", new ExecutionHandler(threadPoolManager));
        pipeline.addLast("executor", executionHandler);
        pipeline.addLast("handler", new GameServerChannelHandler(new GameServerPacketHandlerFactory()));
        return pipeline;
    }

}
