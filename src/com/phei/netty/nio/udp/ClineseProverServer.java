package com.phei.netty.nio.udp;

import com.phei.netty.nio.EchoServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;


/**
 * Created by Angus_Zhu on 8/28/2015.
 */
public class ClineseProverServer {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new ClineseProverServer().bind(port);
    }

    public void bind(int port) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap().group(boosGroup);
            b.channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ChineseProverbServerHandler());


            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            //等待服务端监听端口关闭
            f.channel().closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
        }
    }
}
