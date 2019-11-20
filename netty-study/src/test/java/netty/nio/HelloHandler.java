package netty.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/9
 */
public class HelloHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(HelloHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        System.out.println("开始读取数据============");
        if(msg instanceof ByteBuf){
            ByteBuf req = (ByteBuf)msg;
            String content = req.toString(Charset.defaultCharset());
            LOG.info("content:{}", content);
            ctx.channel().writeAndFlush("李四\r\n");
        }
    }
}
