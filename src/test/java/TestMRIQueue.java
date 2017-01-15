import mriqueue.MostRecentlyInsertedQueue;
import org.junit.Test;

import java.util.Queue;

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
//        Integer[] data = (Integer[])queue.toArray();
//        System.out.println(data);
        //assertEquals(Collections.EMPTY_LIST.toArray(), queue.toArray());
    }


    @Test
    public void testOfferToQueue() {
        Queue<Integer> queue = new MostRecentlyInsertedQueue<Integer>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        assertEquals(queue.size(),3);
        for(Integer item:queue){
            System.out.println(item);
        }

//        Integer[] data = (Integer[])queue.toArray();
//        System.out.println(data);
        //assertEquals(Collections.EMPTY_LIST.toArray(), queue.toArray());
    }
}