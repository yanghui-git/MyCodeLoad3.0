package com.yh.math.other;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> newMessageQueue = new LinkedBlockingQueue<>();
        newMessageQueue.add("a");
        newMessageQueue.add("b");
        newMessageQueue.add("c");
        newMessageQueue.add("d");
        newMessageQueue.add("e");
        while (true) {
            String message = newMessageQueue.take();
            if (message != null) {
                // load
                List<String> messageList = new ArrayList<>();
                messageList.add(message);
                List<String> otherMessageList = new ArrayList<>();
                int drainToNum = newMessageQueue.drainTo(otherMessageList, 2);
                if (drainToNum > 0) {
                    messageList.addAll(otherMessageList);
                }
                System.out.println(messageList);
            }
        }
    }
}
