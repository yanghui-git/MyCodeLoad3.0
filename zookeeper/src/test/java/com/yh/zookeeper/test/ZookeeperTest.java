package com.yh.zookeeper.test;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;

/**
 * zK demo
 * https://blog.csdn.net/java_66666/article/details/81015302
 */
public class ZookeeperTest {

    public ZookeeperTest() throws IOException {
    }

    class WatchYh implements Watcher {

        @SneakyThrows
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getState().equals(Event.KeeperState.SyncConnected)) {
                System.out.println("zk 连接成功");
            }
            if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                System.out.println("配置已修改，新值为：" + new String(zooKeeper.getData(watchedEvent.getPath(), true, new Stat())));
            }
            if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                System.out.println("该配置已被傻屌删除");
            }
        }
    }

/*    private ZooKeeper zooKeeper = new ZooKeeper(
            "121.199.72.238:2181", 5000,
            null
    );*/

    /**
     * 注册监听器
     */
    private ZooKeeper zooKeeper = new ZooKeeper(
            "121.199.72.238:2181", 5000,
            new WatchYh()
    );


    /**
     * 持久节点 断开后 节点依旧存在
     */
    @Test
    public void testOne() throws Exception {
        zooKeeper.create("/yh1", "yh1".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.in.read();
    }


    /**
     * 持久顺序节点 客户端与zookeeper断开连接后，该节点依旧存在，只是Zookeeper给该节点名称进行顺序编号
     */
    @Test
    public void testTwo() throws Exception {
        zooKeeper.create("/yh2", "yh2".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT_SEQUENTIAL);
        System.in.read();
    }

    /**
     * 临时节点 断开后 节点被删除
     */
    @Test
    public void testThree() throws Exception {
        zooKeeper.create("/yh3", "yh3".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        System.in.read();
    }

    /**
     * [yh1, yh40000000015, zookeeper, yh20000000013]
     * <p>
     * 临时顺序节点 客户端与zookeeper断开连接后，该节点被删除，只是Zookeeper给该节点名称进行顺序编号
     */
    @Test
    public void testFour() throws Exception {
        zooKeeper.create("/yh4", "yh4".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.in.read();
    }

    /**
     * 增加节点
     *
     * @throws Exception
     */
    @Test
    public void testFive() throws Exception {
        zooKeeper.create("/yhCreate0", "yhCreate0".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        zooKeeper.create("/yhCreate0/yhCreate1", "yhCreate1".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        zooKeeper.create("/yhCreate0/yhCreate2", "yhCreate2".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.in.read();
    }

    /**
     * 删除
     * [zk: localhost:2181(CONNECTED) 121] get /yhCreate0/yhCreate1/yhCreate3 yhCreate3
     * yhCreate3Test22
     * cZxid = 0x67
     * ctime = Tue Oct 19 07:52:47 UTC 2021
     * mZxid = 0x7f
     * mtime = Tue Oct 19 07:56:11 UTC 2021
     * pZxid = 0x67
     * cversion = 0
     * dataVersion = 4   //version 每改变一次 +1
     * aclVersion = 0
     * ephemeralOwner = 0x0
     * dataLength = 15
     * numChildren = 0
     */
    @Test
    public void testSix() throws Exception {
        zooKeeper.delete("/yhCreate0/yhCreate1/yhCreate3", 0);
        System.in.read();
    }

    /**
     * 修改
     */
    @Test
    public void testSever() throws Exception {
        zooKeeper.setData("/yhCreate0/yhCreate1/yhCreate3", "yhCreate3Test22".getBytes(), 4);
        System.in.read();
    }

    /**
     * 查
     * <p>
     * 没有node 则报错  NoNode for  *
     */
    @Test
    public void testEight() throws Exception {
        byte[] str = zooKeeper.getData("/yhCreate0/yhCreate1/yhCreate3", null, null);
        System.out.println(new String(str));
        System.in.read();
    }

    /**
     * 动态监听--配置更改
     *
     * @throws Exception
     */
    @Test
    public void testNine() throws Exception {
        byte[] str = zooKeeper.getData("/yhCreate0/yhCreate1/yhCreate3", new Watcher() {
            @SneakyThrows
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println("配置已修改，新值为：" + new String(zooKeeper.getData(event.getPath(), true, new Stat())));
                }
            }
        }, null);
        System.out.println(new String(str));
        System.in.read();
    }

    /**
     * 动态监听--配置删除
     *
     * @throws Exception
     */
    @Test
    public void testTen() throws Exception {
        byte[] str = zooKeeper.getData("/yhCreate0/yhCreate10", new Watcher() {
            @SneakyThrows
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println("配置已修改，新值为：" + new String(zooKeeper.getData(event.getPath(), true, new Stat())));
                }
                if (event.getType() == Event.EventType.NodeDeleted) {
                    System.out.println("该配置已被傻屌删除");
                }
            }
        }, null);
        System.out.println(new String(str));
        System.in.read();
    }
}
