package pers.cmy;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/9/12
 */
public class JVMTest {


    @Test
    public void test1() throws Exception {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final long a = System.currentTimeMillis();
        Thread t = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " pass " + (System.currentTimeMillis() - a) + " ms");
                }
            } catch (InterruptedException e) {
                System.out.println("stop......");
            } /*finally {
                System.out.println("finally........");
            }*/
        });
        t.start();

        Thread.sleep(2000);
        t.interrupt();
        System.out.println("interrupt t");
    }

    @Test
    public void test2() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 100;
        });

        boolean complete = future.complete(1);

        //future.join();
        Integer integer = future.get();
        System.out.println("integer = " + integer);

    }
}