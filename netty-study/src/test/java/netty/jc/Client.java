package netty.jc;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/9
 */
public class Client {

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {
        ClientBootstrap bootstrap = new ClientBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        bootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));

        bootstrap.setPipelineFactory(() -> {
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("decoder", new StringDecoder());
            pipeline.addLast("encoder", new StringEncoder());
            pipeline.addLast("hiHandler", new HiHandler());
            return pipeline;
        });

        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("localhost", 10101));
        Channel channel = connect.getChannel();

        LOG.info("client start");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入：");
            channel.write(scanner.next());
        }
    }
}
