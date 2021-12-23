package com.yh.xxlrpc.analyze.route;


/**
 * 机器选举算法
 * 例如 生产者有三个 生产1,生产2,生产3
 * 消费者如何选举机器
 */
public abstract class XxlRpcLoadMethod {

    String[] produceUrl = new String[]{
            "生产1", "生产2", "生产3"
    };

    public abstract String route();

}
