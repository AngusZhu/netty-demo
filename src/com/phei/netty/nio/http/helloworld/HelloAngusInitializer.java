package com.phei.netty.nio.http.helloworld;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * Created by Angus_Zhu on 8/27/2015.
 */
public class HelloAngusInitializer extends ChannelInitializer<SocketChannel> {




    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new HttpServerCodec()).addLast(new HelloAngusServerHandler());
    }
}
