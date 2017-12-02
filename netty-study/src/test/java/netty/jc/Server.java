package netty.jc;


import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/9
 */
public class Server {

    private static final Logger LOG = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {

        ServerBootstrap bootstrap = new ServerBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));

        bootstrap.setPipelineFactory(() -> {
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("decoder",new StringDecoder());
            pipeline.addLast("encoder", new StringEncoder());
            pipeline.addLast("helloHandler", new HelloHandler());
            return pipeline;
        });

        bootstrap.bind(new InetSocketAddress(10101));
        LOG.info("start...");
    }
}
