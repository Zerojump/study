package netty.proto;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.cmy.netty.protobuf.SubscribeReqProto;
import pers.cmy.netty.protobuf.SubscribeRespProto;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/12/6
 */
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(SubReqServerHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq subscribeReq = (SubscribeReqProto.SubscribeReq) msg;
        if ("Will".equalsIgnoreCase(subscribeReq.getUserName())) {
            LOG.info("service accept client subscribe req:{}", subscribeReq.toString());
            ctx.writeAndFlush(resp(subscribeReq.getSubReqID()));
        }
    }

    private SubscribeRespProto.SubscribeResp resp(int subReqID) {
        return SubscribeRespProto.SubscribeResp
                .newBuilder()
                .setSubReqID(subReqID)
                .setRespCode(0)
                .setDesc("Netty book order succeed,3 days later ,sent to the designated address")
                .build();
    }
}
