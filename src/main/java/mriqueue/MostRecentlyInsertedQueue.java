package mriqueue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;


/**
 * Created by vlnik on 1/15/2017.
 */
public class MostRecentlyInsertedQueue<E> extends AbstractQueue<E> implements Queue<E> {

    /**
     * Node class
     */
    static class Node<E> {
        E item;
        Node<E> next;
        Node(E x) {
            item = x;
        }
    }

    /**
     * The capacity bound
     */
    private final int capacity;

    /**
     * Current number of elements
     */
    private int count;
    /**
     * Head of queue.
     * Invariant: head.item == null
     */
    transient Node<E> head;

    /**
     * Tail of queue.
     * Invariant: tail.next == null
     */
    private transient Node<E> tail;

    /**
     * Creates a {@code MostRecentlyInsertedQueue} with the given capacity.
     *
     * @param capacity the capacity of this queue
     * @throws IllegalArgumentException if {@code capacity} is not greater
     *                                  than zero
     */
    public MostRecentlyInsertedQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        this.capacity = capacity;
        this.count = 0;
        tail = head = new Node<E>(null);
    }

    /**
     * Links node at end of queue.
     *
     * @param node the node
     */
    private void enqueue(Node<E> node) {
        tail = tail.next = node;
    }

    /**
     * Removes a node from head of queue.
     *
     * @return the node
     */
    private E dequeue() {
        Node<E> h = head;
        Node<E> first = h.next;
        h.next = h; // help GC
        head = first;
        E x = first.item;
        first.item = null;
        return x;
    }


    public int size() {
        return count;
    }

    /**
     * Inserts the specified element at the tail of this queue.   If this queue
     * is full removes head so that the size ot this queue equals the queue's capacity.
     *
     * @throws NullPointerException if the specified element is null
     */
    public boolean offer(E e) {
        if (e == null) throw new NullPointerException();

        if (count == capacity) {
            dequeue();
            count--;
        }

        Node<E> node = new Node<E>(e);
        if (count < capacity) {
            enqueue(node);
            count++;
        }
        return true;
    }

    public E poll() {
        if (count == 0)
            return null;
        E x = null;

        if (count > 0) {
            x = dequeue();
            count--;
        }
        return x;
    }

    public E peek() {
        if (count == 0)
            return null;
        Node<E> first = head.next;
        if (first == null)
            return null;
        else
            return first.item;
    }

    public boolean remove(Object o) {
        if (o == null) return false;

            for (Node<E> trail = head, p = trail.next;
                 p != null;
                 trail = p, p = p.next) {
                if (o.equals(p.item)) {
                    unlink(p, trail);
                    return true;
                }
            }
            return false;
    }

    /**
     * Unlinks interior Node p with predecessor trail.
     */
    void unlink(Node<E> p, Node<E> trail) {
        p.item = null;
        trail.next = p.next;
        if (tail == p)
            tail = trail;
        count--;
    }

    /**
     * Returns an array containing all of the elements in this queue
     *
     * @return an array containing all of the elements in this queue
     */
    public Object[] toArray() {
            int size = count;
            Object[] a = new Object[size];
            int k = 0;
            for (Node<E> p = head.next; p != null; p = p.next)
                a[k++] = p.item;
            return a;
    }


    /**
     * Returns an iterator over the elements in this queue in proper sequence.
     * The elements will be returned in order from first (head) to last (tail).
     *
     * @return an iterator over the elements in this queue in proper sequence
     */
    public Iterator<E> iterator() {
        return new MRIQItr();
    }

    private class MRIQItr implements Iterator<E> {
        /*
         * Basic weakly-consistent iterator.  At all times hold the next
         * item to hand out so that if hasNext() reports true, we will
         * still have it to return even if lost race with a take etc.
         */

        private Node<E> current;
        private Node<E> lastRet;
        private E currentElement;

        MRIQItr() {
                current = head.next;
                if (current != null)
                    currentElement = current.item;
        }

        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next live successor of p, or null if no such.
         *
         * Unlike other traversal methods, iterators need to handle both:
         * - dequeued nodes (p.next == p)
         * - (possibly multiple) interior removed nodes (p.item == null)
         */
        private Node<E> nextNode(Node<E> p) {
            for (;;) {
                Node<E> s = p.next;
                if (s == p)
                    return head.next;
                if (s == null || s.item != null)
                    return s;
                p = s;
            }
        }

        public E next() {
                if (current == null)
                    throw new NoSuchElementException();
                E x = currentElement;
                lastRet = current;
                current = nextNode(current);
                currentElement = (current == null) ? null : current.item;
                return x;
        }

     /*   public void remove() {
            if (lastRet == null)
                throw new IllegalStateException();

                Node<E> node = lastRet;
                lastRet = null;
                for (Node<E> trail = head, p = trail.next;
                     p != null;
                     trail = p, p = p.next) {
                    if (p == node) {
                        unlink(p, trail);
                        break;
                    }
                }
        }*/
    }

}
