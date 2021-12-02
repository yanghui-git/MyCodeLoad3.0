package com.yh.springeasy.sync;

import org.omg.CORBA.Object;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * spring 默认 scope 为单例模式
 * 结论总结 : controller 自定义变量线程不安全
 * 解决方案 1 synchronized  2 lock 3 AtomicInteger
 * <p>
 * *****
 *
 * @scope=@Scope("prototype") ThreadLocal
 */

/**
 * Controller 层并发安全探究
 * https://www.jianshu.com/p/eba1fef77442#comments
 */
@RestController
@RequestMapping("/sync")
public class SyncController {

    private int i;

    private Lock lock = new ReentrantLock();

    private AtomicInteger result = new AtomicInteger(0);

    /**
     * synchronized
     */
  /*  @GetMapping
    public synchronized void syncTest() {
        System.out.println("并发模拟i=" + (i++) + "   当前请求时间" + LocalDateTime.now());
    }
*/

    /**
     * lock
     */
/*    @GetMapping
    public void syncTest() {
        lock.lock();
        System.out.println("并发模拟i=" + (i++) + "   当前请求时间" + LocalDateTime.now());
        lock.unlock();
    }*/


 /*   @GetMapping
    public void syncTest() {
        result.incrementAndGet();
        System.out.println("并发模拟i=" + result + "   当前请求时间" + LocalDateTime.now());
    }*/

    /**
     * 正常测试
     */
    @GetMapping
    public void syncTest() {
        System.out.println("并发模拟i=" + (i++) + "   当前请求时间" + LocalDateTime.now());
    }

}
