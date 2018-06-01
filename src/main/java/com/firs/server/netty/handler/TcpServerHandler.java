/**
 * @author chenzy 20180529 TCP消息处理器
 */
package com.firs.server.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firs.server.mq.JmsSender;

@Component
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

	public static TcpServerHandler tcpServerHandler; // 为了解决Autowired无法注入的问题

	public TcpServerHandler() {
	}

	private static final Logger logger = LoggerFactory
			.getLogger(TcpServerHandler.class);

	@Autowired
	private JmsSender sender;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf inBuffer = (ByteBuf) msg;
		String received = inBuffer.toString(CharsetUtil.UTF_8);
		System.out.println("Tcp Server received:" + received);
		tcpServerHandler.sender.sendByTopic(received);
		ctx.write(Unpooled.copiedBuffer("Hello " + received, CharsetUtil.UTF_8));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
		/* .addListener(ChannelFutureListener.CLOSE); */
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	// 把注入的sender赋值给tcpServerHandler
	@PostConstruct
	public void init() {
		tcpServerHandler = this;
		tcpServerHandler.sender = this.sender;
	}
}
