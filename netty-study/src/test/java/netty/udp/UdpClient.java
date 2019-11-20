package netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2019/7/31
 */
public class UdpClient {
    public static final int MessageReceived = 0x99;
    private static int scanPort = 21;

    public UdpClient(int scanPort) {
        this.scanPort = scanPort;
    }


    private static class CLientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
            String body = packet.content().toString(CharsetUtil.UTF_8);
            System.out.println(body);
        }
    }

    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new CLientHandler());

            Channel ch = b.bind(0).sync().channel();

            ch.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("from client", CharsetUtil.UTF_8),
                    new InetSocketAddress("127.0.0.1", scanPort))).sync();

            ch.closeFuture().await();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
