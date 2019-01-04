package project01;


public class Queue<T> {

    private Object[] q; // array of elements
    int N = 0; // number of elements on queue private
    int first = 0; // index of first element of queue
    int last = 0; // index of next available slot


    public Queue(int size) {
        q = new Object[size];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int search(T key) {
        for (int i = 0; i < N; i++) {
            if (q[(first + i) % q.length].equals(key)) {
                return (first + i) % q.length;
            }
        }
        return -1;
    }

    public T getData(int index) {

        int realIndex = q.length - N + index;

        if (realIndex >= q.length) {
            return null;
        }
        return (T) q[realIndex];
    }

    // Dequeue remove the first candy
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Object item = q[first];
        q[first] = null;
        N--;
        first++;
        if (first == q.length) {
            first = 0;           // wrap-around
        }        // shrink size of array if necessary
        if (N > 0 && N == q.length / 4) {
            resize(q.length / 2);
        }
        return (T) item;
    }

    // Enqueue, put a new candy to the last
    public void enqueue(T element) {
        // double size of array if necessary and recopy to front of array
        if (N == q.length) {
            resize(2 * q.length);   // double size of array if necessary
        }
        if (N == 0) {
            first = 0;
            last = 0;
        }
        if (last == q.length) {
            last = 0;          // wrap-around
        }
        q[last] = element; // add candy
        last++;
        N++;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < N; i++) {
            s += (q[(first + i) % q.length] + " ");
        }
        return s;
    }

    public void resize(int newSize) {
        Object[] temp = new Object[newSize];
        for (int i = 0; i < N; i++) {
            temp[i] = q[i];
        }
        q = temp;
    }
}



