package netty.nio;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/9
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        LOG.info("read data");
        ctx.channel().attr(AttributeKey.valueOf("ChannelKey")).set(msg.toString());
        ctx.channel().close();
    }
}
