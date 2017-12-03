package pers.cmy;

import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/25
 */
public class RedissonTest {
    private RedissonClient client;

    @Before
    public void setUp() throws Exception {
        //Config config = new Config();
        client = Redisson.create();
    }

    @Test
    public void test01() throws Exception {
        RBucket<Object> xxx = client.getBucket("xxx");
    }
}
