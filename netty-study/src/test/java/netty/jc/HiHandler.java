package netty.jc;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/9
 */
public class HiHandler extends SimpleChannelHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HiHandler.class);

    public HiHandler() {
        super();
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Object message = e.getMessage();
        //LOG.info("hiHandler message:{}", message);

        //ChannelBuffer channelBuffer = (ChannelBuffer) message;

        LOG.info("hiHandler msg:{}", message);

        super.messageReceived(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        LOG.info("hiHandler exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        LOG.info("hiHandler channelOpen");
        super.channelOpen(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        LOG.info("hiHandler channelConnected");
        super.channelConnected(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        LOG.info("hiHandler channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        LOG.info("hiHandler channelClosed");
        super.channelClosed(ctx, e);
    }
}
