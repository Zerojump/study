package com.cmy;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by Lankidd on 2017/3/8.
 */
public class ElasticsearchDemoTest {

    @Test
    public void test1() throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.38.129"), 9300));

        // on shutdown

        client.close();
    }
}
