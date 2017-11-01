package aio;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/1
 */
public class AsyncTimeTest {
    @Test
    public void testAsync() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);

        AsyncTimeServerHandler serverHandler = new AsyncTimeServerHandler(9503);
        new Thread(serverHandler, "serverHandler").start();

        AsyncTimeClientHandler clientHandler = new AsyncTimeClientHandler("127.0.0.1", 9503);
        new Thread(clientHandler,"clientHandler").start();

        latch.await(30, TimeUnit.SECONDS);
    }
}
