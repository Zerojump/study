package nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/1
 */
public class TimeClientHandle extends BaseTimeHandle implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(TimeClientHandle.class);

    private String host;

    private int port;

    private SocketChannel socketChannel;

    public TimeClientHandle(String host, int port, CountDownLatch latch) {
        super.latch = latch;
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        stop = false;

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stop) {
            iter(selector);
        }
    }


    @Override
    protected void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            LOG.info("Time client key isValid");

            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {

                LOG.info("Time client key isConnectable");

                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc, "QUERY TIME ORDER");
                } else {
                    System.exit(1);
                }
            }

            if (key.isReadable()) {
                LOG.info("Time client key isReadable");

                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "utf8");
                    System.out.println("body = " + body);
                    stop();
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doConnect() throws IOException {
        LOG.info("Time client doConnect");
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            doWrite(socketChannel, "QUERY TIME ORDER");
        }
    }

    protected void doWrite(SocketChannel sc, String body) throws IOException {
        LOG.info("Time client doWrite");
        byte[] req = body.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        sc.write(writeBuffer);

        if (!writeBuffer.hasRemaining()) {
            LOG.info("Send order to server succeed.");
        }
    }
}
