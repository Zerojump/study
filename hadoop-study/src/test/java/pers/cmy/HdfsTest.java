package pers.cmy;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/12
 */
public class HdfsTest {

    private static final Logger LOG = LoggerFactory.getLogger(HdfsTest.class);

    private static final String HDFS_PATH = "hdfs://192.168.38.130:8020";

    private FileSystem fileSystem = null;
    private Configuration configuration = null;

    @Test
    public void mkdir() throws Exception {
        LOG.info("mkdir");
        fileSystem.mkdirs(new Path("/tmp/dir01"));
    }

    @Before
    public void setUp() throws Exception {
        LOG.info("init...");
        configuration = new Configuration();
        fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, "ruby");
    }

    @After
    public void tearDown() throws Exception {
        configuration = null;
        fileSystem = null;
        LOG.info("shutdown...");
    }
}
