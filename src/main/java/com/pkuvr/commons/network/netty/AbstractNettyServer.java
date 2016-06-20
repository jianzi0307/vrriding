/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pkuvr.commons.network.netty;

import com.pkuvr.commons.utils.ThreadPoolManager;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.nio.ByteOrder;

/**
 * 客户端和服务器端的基本参数配置
 *
 * @author lyahim
 */
public abstract class AbstractNettyServer {
    @Resource
    private ThreadPoolManager threadPoolManager;

    /**
     * Base method for initialize Client ChannelFactorys
     * 初始化客户端工厂的方法
     *
     * @return NioServerSocketChannelFactory
     */
    protected NioClientSocketChannelFactory initClientChannelFactory() {
        return new NioClientSocketChannelFactory(threadPoolManager, threadPoolManager, Runtime.getRuntime().availableProcessors()); // Java 虚拟机返回可用处理器的数目。
    }

    /**
     * Base method for initialize Server ChannelFactorys
     * 初始化服务器端工厂的方法
     *
     * @return NioServerSocketChannelFactory
     */
    protected NioServerSocketChannelFactory initServerChannelFactory() {
        return new NioServerSocketChannelFactory(threadPoolManager, threadPoolManager, Runtime.getRuntime().availableProcessors()); // Java 虚拟机返回可用处理器的数目。
    }

    /**
     * Base shutdown process for server
     * 停止服务器的方法(通知线程池停)
     */
    public void shutDown() {
        threadPoolManager.shutdown();
    }

    /**
     * 服务器端的基本配置
     *
     * @param channelFactory
     * @param address
     * @param channelPipelineFactory
     * @return
     */
    protected Channel initServerChannel(ChannelFactory channelFactory, InetSocketAddress address, ChannelPipelineFactory channelPipelineFactory) {
        ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);
        bootstrap.setPipelineFactory(channelPipelineFactory);
        bootstrap.setOption("child.bufferFactory", HeapChannelBufferFactory.getInstance(ByteOrder.LITTLE_ENDIAN));
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.setOption("child.reuseAddress", true);
        bootstrap.setOption("child.connectTimeoutMillis", 100);
        bootstrap.setOption("readWriteFair", true);
        return bootstrap.bind(address);
    }

    /**
     * 客户端的基本配置
     *
     * @param channelFactory
     * @param address
     * @param channelPipelineFactory
     * @return
     */
    protected ChannelFuture initClientChannel(ChannelFactory channelFactory, InetSocketAddress address, ChannelPipelineFactory channelPipelineFactory) {
        ClientBootstrap bootstrap = new ClientBootstrap(channelFactory);
        bootstrap.setPipelineFactory(channelPipelineFactory);
        bootstrap.setOption("bufferFactory", HeapChannelBufferFactory.getInstance(ByteOrder.LITTLE_ENDIAN));
        bootstrap.setOption("keepAlive", true);

        return bootstrap.connect(address);
    }

    /**
     * Initialize NettyServer
     */
    public abstract void initialize();

}
