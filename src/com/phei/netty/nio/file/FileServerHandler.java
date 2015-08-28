package com.phei.netty.nio.file;

import io.netty.channel.*;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;

/**
 * Created by Angus_Zhu on 8/28/2015.
 */
public class FileServerHandler extends SimpleChannelInboundHandler<String>{

    public static final String LS = " " + System.getProperty("line.separator");
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {

        File file = new File(msg);
        if (file.exists()) {
            if (!file.isFile()) {
                ctx.writeAndFlush("not a file try again:"+ file +LS);
                return ;
            }
            ctx.writeAndFlush(file + " " + file.length() + LS);

            RandomAccessFile randomAccessFile = new RandomAccessFile(msg, "r");
            FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());
            ctx.write(region);
            ctx.writeAndFlush(LS);
            randomAccessFile.close();
        }else {
            ctx.writeAndFlush("file not found:" + file + LS);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
