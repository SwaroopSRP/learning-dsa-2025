import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack<T> {
    private ArrayList<T> list;

    Stack() {
        list = new ArrayList<>();
    }

    Stack(int initialCapacity) {
        list = new ArrayList<>(initialCapacity);
    }

    public void push(T value) {
        list.add(value);
    }

    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();

        return list.remove(list.size() - 1);
    }

    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();

        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "Stack" + list.toString(); 
    }
}
