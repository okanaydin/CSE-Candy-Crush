package project01;

public class Stack<T> {

    Object[] stackArray;
    int top = 0;

    public Stack(int size) {
        stackArray = new Object[size];
    }

    // add element
    public void push(T data) {
        if (top == 50) {
            System.out.println("Stack is full");
        }
        stackArray[top] = data;
        top++;
    }

    public T peek() {
        return (T) stackArray[top];
    }

    //delete element
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        }

        T data;
        top--;
        data = (T) stackArray[top];
        stackArray[top] = 0;
        return data;
    }

    public T elementAt(int index) {
        int realIndex = stackArray.length - top + index;

        if (realIndex >= stackArray.length) {
            return null;
        }
        return (T) stackArray[index];
    }

    public int size() {
        return top;
    }

    public boolean isEmpty() {
        return top <= 0;
    }

}
