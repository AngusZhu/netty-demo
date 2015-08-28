package com.phei.netty.nio.udp;

import com.phei.netty.nio.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;


/**
 * Created by Angus_Zhu on 8/25/2015.
 */
public class ChineseProverbClient {
    public void connect(int port  ){
        EventLoopGroup clientGroup=new NioEventLoopGroup();

        try {
            Bootstrap bs=new Bootstrap();
            bs.group(clientGroup).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ChineseProverbClientHandler()
                    );
            Channel ch = bs.bind(0).sync().channel();
            ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("Query Proverb?", CharsetUtil.UTF_8), new InetSocketAddress("255.255.255.255", port))).sync();
            if (!ch.closeFuture().await(15000)) {
                System.out.println("timed out!");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            clientGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChineseProverbClient().connect(8080);
    }
}
