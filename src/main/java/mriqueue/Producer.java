package mriqueue;

import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vlnik on 1/22/2017.
 */
public class Producer implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(Producer.class);
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
            int offered = ++counter + threadNumber;
            productChannel.offer(offered);
            logger.info("Offered: {}", offered);
            try {
                Thread.sleep(250);
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
        logger.info("Producer {} stops: channel content is [ {} ]", threadNumber, buffer.toString());
    }
}