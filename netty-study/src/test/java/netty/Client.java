package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/9
 */
public class Client {

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$_".getBytes())))
                                //.addLast(new LineBasedFrameDecoder(1024))
                                .addLast("decoder", new StringDecoder())
                                .addLast(new EchoClientHandler())
                        //.addLast("hiHandler", new ClientHandler())
                        //.addLast(new TimeClientHandler())
                        //.addLast("encoder", new StringEncoder())
                        ;
                    }
                });
        ChannelFuture future;
        try {
            future = bootstrap.connect("localhost", 10102).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
