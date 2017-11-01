package aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/1
 */
public class AsyncTimeServerHandler implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncTimeServerHandler.class);

    private int port;

    private CountDownLatch latch;

    private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        asynchronousServerSocketChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler>() {
            @Override
            public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
                attachment.asynchronousServerSocketChannel.accept(attachment, this);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                result.read(buffer, buffer, new ReadCompletionHandler(result));
            }

            @Override
            public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
                exc.printStackTrace();
                attachment.latch.countDown();
            }
        });
    }

    private static class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
        private AsynchronousSocketChannel channel;

        public ReadCompletionHandler(AsynchronousSocketChannel channel) {
            if (channel != null) {
                this.channel = channel;
            }
        }

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            attachment.flip();
            byte[] bytes = new byte[attachment.remaining()];
            attachment.get(bytes);

            try {
                String body = new String(bytes, "utf8");
                LOG.info("Time server receive order:{}", body);
                doWrite(channel, "QUERY TIME ORDER".equalsIgnoreCase(body) ? LocalDateTime.now().toString() : "bad order");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        private void doWrite(AsynchronousSocketChannel channel, String body) {
            byte[] bytes = body.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if (attachment.hasRemaining()) {
                        channel.write(buffer, buffer, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                this.channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
