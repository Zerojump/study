package nio;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/1
 */
public class TimeServer {

    @Test
    public void testServer() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        MultiplexTimeServer timeServer = new MultiplexTimeServer(9502, latch);
        new Thread(timeServer,"timeServer").start();

        TimeClientHandle clientHandle = new TimeClientHandle(null, 9502, latch);
        new Thread(clientHandle, "clientHandle").start();

        latch.await(30, TimeUnit.SECONDS);
    }
}
