package com.pkuvr.game_server;

import com.pkuvr.commons.network.netty.AbstractNettyServer;
import com.pkuvr.game_server.network.GameServerPipeLineFactory;
import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

public class NettyGameServer extends AbstractNettyServer {
    private static final Logger logger = Logger.getLogger(NettyGameServer.class);
    private ChannelFactory clientToGameChannelFactory; // 是一个创建和管理Channel通道及其相关资源的工厂接口，它处理所有的I/O请求并产生相应的I/O ChannelEvent通道事件。
    private int gameSocketPort;// 10007
    @Resource
    private ChannelGroup channelGroup;
    @Resource
    private GameServerPipeLineFactory gameServerPipeLineFactory; // 为了给每一个Channel通道创建一个新的Handler实例，我们需要实现一个ChannelPipelineFactory管道工厂

    public NettyGameServer(int gameSocketPort) {
        this.gameSocketPort = gameSocketPort;
        logger.info("NettyGameServer started!");
    }

    @Override
    public void initialize() {
        clientToGameChannelFactory = initServerChannelFactory();
        channelGroup.add(initServerChannel(clientToGameChannelFactory, new InetSocketAddress(gameSocketPort), gameServerPipeLineFactory));
        logger.info("Game Server started");
    }

    @Override
    public void shutDown() {
        ChannelGroupFuture future = channelGroup.close();
        future.awaitUninterruptibly();
        clientToGameChannelFactory.releaseExternalResources();
        super.shutDown();
    }
}
