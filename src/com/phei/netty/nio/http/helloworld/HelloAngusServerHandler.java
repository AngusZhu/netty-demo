package com.phei.netty.nio.http.helloworld;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;;

/**
 * Created by Angus_Zhu on 8/27/2015.
 */
public class HelloAngusServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;
            if (HttpHeaderUtil.is100ContinueExpected(req)) {
                ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
            }
            boolean isKeepAlive = HttpHeaderUtil.isKeepAlive(req);
            FullHttpResponse resp = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer("Angus Template".getBytes()));
            resp.headers().set(CONTENT_TYPE, "text/plain");
            resp.headers().setInt(CONTENT_LENGTH, resp.content().readableBytes());

            if (!isKeepAlive) {
                ctx.write(resp).addListener(ChannelFutureListener.CLOSE);
            }else {
                resp.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                ctx.write(resp);
            }
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
