import mriqueue.MostRecentlyInsertedQueue;
import mriqueue.MostRecentlyInsertedBlockingQueue;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestMRIQueue {
    @Test
    public void testJUnit() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }

    @Test
    public void testEmptyQueue() {
        Queue<Integer> queue = new MostRecentlyInsertedQueue<Integer>(3);
        assertEquals(queue.size(),0);
    }

    @Test
    public void testOfferToQueue() {
       // Queue<Integer> queue = new MostRecentlyInsertedQueue<Integer>(3);
        Queue<Integer> queue = new MostRecentlyInsertedBlockingQueue<Integer>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        assertEquals(queue.size(),3);
        assertArrayEquals(queue.toArray(), new Object[]{3, 4, 5});
        for(Integer item:queue){
            System.out.println(item);
        }
        Integer p = queue.poll();
        System.out.println("Polled item = " + p);
        for(Integer item:queue){
            System.out.println(item);
        }
        p = queue.poll();
        System.out.println("Polled item = " + p);
        for(Integer item:queue){
            System.out.println(item);
        }
        p = queue.poll();
        System.out.println("Polled item = " + p);
        for(Integer item:queue){
            System.out.println(item);
        }
        p = queue.poll();
        System.out.println("Polled item = " + p);
        for(Integer item:queue){
            System.out.println(item);
        }
    }
}