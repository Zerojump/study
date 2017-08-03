package com.cmy;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/8/1
 */
public class ZKTest {
    private static final String hostPort = "192.168.38.130:2181";
    private static final Master m = new Master(hostPort);
    private static final Random random = new Random();


    public static class Master implements Watcher {
        ZooKeeper zk;
        String hostPort;

        Master(String hostPort) {
            this.hostPort = hostPort;
        }

        void startZK() throws IOException {
            zk = new ZooKeeper(hostPort, 15000, this);
        }

        void stopZK() throws InterruptedException {
            if (zk != null) {
                zk.close();
            }
        }

        boolean runForMaster(String path, String data) throws InterruptedException {
            while (true) {
                try {
                    String result = zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    System.out.println("result = " + result);

                    return true;
                } catch (KeeperException.NodeExistsException e) {
                    return false;
                } catch (KeeperException e) {
                }

                Boolean isMaster = checkMaster(path, data);
                if (isMaster != null) {
                    return isMaster;
                }
            }
        }

        private Boolean checkMaster(String path, String data) {
            boolean flag = true;
            while (flag) {
                try {
                    Stat stat = new Stat();
                    byte[] dataByte = zk.getData(path, false, stat);
                    return new String(dataByte).equals(data);
                } catch (KeeperException.NoNodeException e) {
                    flag = false;
                } catch (KeeperException | InterruptedException e) {
                }
            }
            return null;
        }

        static boolean isMaster;

        void runForMasterAysn(String path, String data) {
            zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                    (rc, p, ctx, name) -> {
                        switch (KeeperException.Code.get(rc)) {
                            case CONNECTIONLOSS:
                                checkMasterAysn(p, data);
                                return;
                            case OK:
                                isMaster = true;
                                break;
                            default:
                                isMaster = false;
                        }
                    }, null);
        }

        void checkMasterAysn(String path, String data) {
            zk.getData(path, false, (rc, p, ctx, dataByte, stat) -> {
                switch (KeeperException.Code.get(rc)) {
                    case CONNECTIONLOSS:
                        checkMasterAysn(p, data);
                        return;
                    case NONODE:
                        runForMasterAysn(path, data);
                        return;
                    default:
                }
            }, null);
        }

        String getDate(String path, boolean watch) throws KeeperException, InterruptedException, UnsupportedEncodingException {
            byte[] bytes = zk.getData(path, watch, null);
            return new String(bytes, "utf8");
        }

        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("watchedEvent = " + watchedEvent);
        }
    }

    @After
    public void tearDown() throws Exception {
        m.stopZK();
    }

    @Test
    public void test1() throws Exception {
        m.startZK();

        //String input = null;
        //while (!"".equals(input)) {
        //    //input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        //    Scanner s = new Scanner(System.in);
        //    input = s.nextLine();
        //}
    }

    @Test
    public void test2() throws Exception {
        m.startZK();
        String path = "/master";
        String serverId = Integer.toHexString(random.nextInt());
        boolean isMaster = m.runForMaster(path, serverId);
        System.out.println("isMaster = " + isMaster);
        String s = m.getDate(path, false);
        System.out.println("s = " + s);
        Thread.sleep(5000);
    }

    @Test
    public void test3() throws Exception {
        m.startZK();
        String path = "/master";
        String serverId = Integer.toHexString(random.nextInt());
        m.runForMasterAysn(path, serverId);
        Thread.sleep(3000);
        System.out.println("isMaster = " + Master.isMaster);
        String s = m.getDate(path, false);
        System.out.println("s = " + s);
        Thread.sleep(3000);
    }
}
