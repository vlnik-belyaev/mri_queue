package mriqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vlnik on 1/22/2017.
 */
public class Consumer  implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
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
                //Integer polledData = productChannel.poll();
                Integer polledData = productChannel.poll(200, TimeUnit.MILLISECONDS);
                logger.info("Polling: {}", polledData);

                Integer takenData = productChannel.take();
                logger.info("Taken: {}", takenData);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printContent();
    }

    private void printContent() {
        StringBuffer buffer = new StringBuffer();
        for (Integer item : this.productChannel) {
            buffer.append(item + " ");
        }
        logger.info("Consumer {} stops: channel content is [ {} ]", threadNumber, buffer.toString());
    }
}