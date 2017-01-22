package mriqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by vlnik on 1/22/2017.
 */
public class Consumer  implements Runnable{
    BlockingQueue<Integer> productChannel;
    int counter;
    int threadNumber;

    Consumer(BlockingQueue channel, int threadNumber){
        this.productChannel = channel;
        this.counter = 0;
        this.threadNumber = threadNumber;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (counter++ < 50) {
            try {
                int channelData = productChannel.take();
                System.out.println("Processed:" + channelData);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printContent();
    }

    private void printContent() {
        synchronized (productChannel) {
            System.out.print("Consumer " + threadNumber + " : channel content is [");
            for (Integer item : this.productChannel) {
                System.out.print(item + " ");
            }
            System.out.println("]");
        }
    }
}