import mriqueue.MostRecentlyInsertedBlockingQueue;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestBlockingQueue {

    @Test
    public void testEmptyQueue() {
        Queue<Integer> queue = new MostRecentlyInsertedBlockingQueue<Integer>(3);
        assertEquals(queue.size(), 0);
    }

    @Test
    public void testPeekEmptyQueue() {
        Queue<Integer> queue = new MostRecentlyInsertedBlockingQueue<Integer>(3);
        assertNull(queue.peek());
    }

    @Test
    public void testPollEmptyQueue() {
        Queue<Integer> queue = new MostRecentlyInsertedBlockingQueue<Integer>(3);
        assertNull(queue.poll());
    }

    @Test
    public void testPollQueue() {
        Queue<Integer> queue = new MostRecentlyInsertedBlockingQueue<Integer>(3);
        queue.offer(1);
        assertEquals(queue.poll(), new Integer(1));
        assertEquals(queue.size(), 0);
    }

    @Test
    public void testOfferToQueue() {
        Queue<Integer> queue = new MostRecentlyInsertedBlockingQueue<Integer>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        assertEquals(queue.size(), 3);
        assertArrayEquals(queue.toArray(), new Object[]{3, 4, 5});
    }
}