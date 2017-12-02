package netty.jc;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.omg.DynamicAny._DynStructStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/9
 */
public class HelloHandler extends SimpleChannelHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HelloHandler.class);

    public HelloHandler() {
        super();
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Object message = e.getMessage();
        //LOG.info("message:{}", message);

        //ChannelBuffer channelBuffer = (ChannelBuffer) message;

        LOG.info("msg:{}", message);

        //ChannelBuffer copiedBuffer = ChannelBuffers.copiedBuffer("hi".getBytes());
        ctx.getChannel().write("hi");


        super.messageReceived(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        LOG.info("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        LOG.info("channelOpen");
        super.channelOpen(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        LOG.info("channelConnected");
        super.channelConnected(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        LOG.info("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        LOG.info("channelClosed");
        super.channelClosed(ctx, e);
    }
}
