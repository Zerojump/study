package nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/1
 */
public class MultiplexTimeServer extends BaseTimeHandle implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(MultiplexTimeServer.class);

    private ServerSocketChannel serverChannel;

    public MultiplexTimeServer(int port, CountDownLatch latch) {
        super.latch = latch;
        stop = false;
        //1、打开ServerSocketChannel
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);

            //2、绑定监听地址
            serverChannel.socket().bind(new InetSocketAddress("127.0.0.1", port), 1024);

            //3、创建 selector
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            LOG.info("The time server is start in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!stop) {
            iter(selector);
        }
    }

    @Override
    protected void handleInput(SelectionKey key) throws IOException {
        //A key is valid upon creation and remains so until it is cancelled,its channel is closed, or its selector is closed.
        if (key.isValid()) {
            //LOG.info("Time server key.isValid");

            //Tests whether this key's channel is ready to accept a new socket connection.
            if (key.isAcceptable()) {
                LOG.info("Time server key isAcceptable");

                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
                //LOG.info("Time server key isReadable");

                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);

                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "utf8");
                    LOG.info("Time server receive body = " + body);
                    doWrite(sc, "QUERY TIME ORDER".equalsIgnoreCase(body) ? LocalDateTime.now().toString() : "bad order");
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    protected void doWrite(SocketChannel sc, String body) throws IOException {
        LOG.info("Time server doWrite");
        if (body != null && body.trim().length() > 0) {
            byte[] bytes = body.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
        }
    }
}
