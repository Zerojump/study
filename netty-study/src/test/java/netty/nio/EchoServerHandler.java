package netty.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/12/6
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(TimeServerHandler.class);

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        LOG.info("counter:{}, receive body:{}", counter, body);
        body += "$_";

        ByteBuf byteBuf = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
