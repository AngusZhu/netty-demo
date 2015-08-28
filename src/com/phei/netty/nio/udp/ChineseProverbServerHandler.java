package com.phei.netty.nio.udp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Angus_Zhu on 8/28/2015.
 */
public class ChineseProverbServerHandler extends
        SimpleChannelInboundHandler<DatagramPacket> {
    private static final String[] DICTIONARY = {"老师打开附","件是的话", "收到任何","空间收到","任何空间收到货", "就是你电","话开机","速度任何","事都","你很快就","收到"};

    private String nextQuote() {
        int quteId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
        return DICTIONARY[quteId];
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        String req = msg.content().toString(CharsetUtil.UTF_8);
        System.out.println(req);
        if ("Query Proverb?".equals(req)) {
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("the query result:" + nextQuote(), CharsetUtil.UTF_8), msg.sender()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
