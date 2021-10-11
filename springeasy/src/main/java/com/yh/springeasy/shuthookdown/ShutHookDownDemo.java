package com.yh.springeasy.shuthookdown;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *
 * https://blog.csdn.net/fututadeyoushang/article/details/80941632?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0.no_search_link&spm=1001.2101.3001.4242
 *
 * ShutdownHook - 优雅地停止服务
 *
 */
@Component
public class ShutHookDownDemo {

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Spring boot 优雅下线demo......." + LocalDateTime.now().toString());
            }
        });
    }

}
