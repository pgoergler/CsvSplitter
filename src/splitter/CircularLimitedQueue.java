package splitter;

import java.util.LinkedList;

/**
 *
 * @author paul
 */
public class CircularLimitedQueue<E> extends LinkedList<E> {

    protected int limit;

    public CircularLimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        boolean added = super.add(o);
        while (added && size() > limit) {
            super.remove();
        }
        return added;
    }
}
