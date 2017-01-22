package mriqueue;

import java.util.concurrent.BlockingQueue;

/**
 * This test class demonstrates MostRecentlyInsertedBlockingQueue.
 * Each producer and consumer prints the content of the shared collection at the end.
 * Created by vlnik on 1/22/2017.
 */
public class TestApp {
    public static void main(String[] args) {
        BlockingQueue<Integer> data = new MostRecentlyInsertedBlockingQueue<Integer>(20);
        for (int i = 0; i < 5; i++) {
            Producer producer = new Producer(data,i);
            Consumer consumer = new Consumer(data,i);
        }
    }
}
