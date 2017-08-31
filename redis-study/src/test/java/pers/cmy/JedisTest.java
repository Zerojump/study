package pers.cmy;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/8/22
 */
public class JedisTest {
    @Test
    public void test1() throws Exception {
        Jedis jedis = new Jedis("localhost", 6379);
    }
}
