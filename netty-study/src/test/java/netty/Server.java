package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/9
 */
public class Server {

    private static final Logger LOG = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {

        ServerBootstrap bootstrap = new ServerBootstrap();

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                //固定长度解码器
                                .addLast(new FixedLengthFrameDecoder(20))
                                //自定义分隔符解码器
                                //.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$_".getBytes())))
                                //换行符作为分隔符的解码器，依次遍历ByteBuf中的字节，如果有"\n" or "\r\n"，就以此位置作为结束位置
                                //.addLast(new LineBasedFrameDecoder(1024))
                                //仅仅是接收对象并转成字符串
                                .addLast("decoder", new StringDecoder())
                                .addLast(new EchoServerHandler())
                        //.addLast("helloHandler", new HelloHandler())
                        //.addLast(new TimeServerHandler())
                        //.addLast("encoder", new StringEncoder())
                        ;
                    }
                });

        try {
            LOG.info("start...");
            ChannelFuture future = bootstrap.bind(10102).sync();
            LOG.info("started...");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
