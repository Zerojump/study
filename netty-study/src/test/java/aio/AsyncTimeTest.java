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

    @Test
    public void name() throws Exception {
        //byte[] array = new byte[4];
        //int index = 1;
        //(array[index]     & 0xff) << 24 |
        //        (array[index + 1] & 0xff) << 16 |
        //        (array[index + 2] & 0xff) <<  8 |
        //        array[index + 3] & 0xff;
        //System.out.println();
    }

    @Test
    public void name2() throws Exception {
        byte b = -2;
        System.out.println(b << 1);

    }
}
