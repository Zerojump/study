package nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/1
 */
public abstract class BaseTimeHandle {

    private static final Logger LOG = LoggerFactory.getLogger(BaseTimeHandle.class);

    protected CountDownLatch latch;

    protected Selector selector;

    protected volatile boolean stop;


    protected void iter(Selector selector) {
        try {
            selector.select(1000);
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            for (Iterator<SelectionKey> it = selectionKeySet.iterator(); it.hasNext(); ) {
                SelectionKey key = it.next();
                it.remove();

                try {
                    handleInput(key);
                } catch (Exception e) {
                    if (key != null) {
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void stop() {
        this.stop = true;
        latch.countDown();
        LOG.info("latch.countDown()...");
    }


    protected abstract void handleInput(SelectionKey key) throws IOException;

    protected abstract void doWrite(SocketChannel sc, String body) throws IOException;
}
