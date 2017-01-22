package mriqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by vlnik on 1/22/2017.
 */
public class Producer implements Runnable{
    BlockingQueue<Integer> productChannel;
    int counter;
    int threadNumber;

    Producer(BlockingQueue channel, int threadNumber){
        this.productChannel = channel;
        this.counter = 0;
        this.threadNumber = threadNumber;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (counter < 100) {
            productChannel.offer(counter++);
            System.out.println("Added:" + counter);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printContent();
    }
    private void printContent() {
        synchronized (productChannel) {
            System.out.print("Producer "+threadNumber+" : channel content is [");
            for (Integer item : this.productChannel) {
                System.out.print(item + " ");
            }
            System.out.println("]");
        }
    }
}