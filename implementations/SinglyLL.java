public class SinglyLL<T> {
    private class Node {
        T data;
        Node next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node(T data) {
            this(data, null);
        }
    }

    private Node head;
    private int size;

    public SinglyLL() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void insertAtHead(T value) {
        head = new Node(value, head);
        size++;
    }

    public void append(T value) {
        if (head == null) {
            head = new Node(value);
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new Node(value);
        }
        size++;
    }

    public void insert(int position, T value) {
        if (position <= 0) {
            insertAtHead(value);
            return;
        }
        if (position >= size) {
            append(value);
            return;
        }
        Node curr = head;
        for (int i = 0; i < position - 1; i++) {
            curr = curr.next;
        }
        curr.next = new Node(value, curr.next);
        size++;
    }

    public T removeAtHead() {
        if (isEmpty()) {
            throw new IllegalStateException("Can't remove, list already empty!");
        }
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Can't remove, list already empty!");
        }
        if (size == 1) {
            return removeAtHead();
        }
        Node curr = head;
        while (curr.next.next != null) {
            curr = curr.next;
        }
        T data = curr.next.data;
        curr.next = null;
        size--;
        return data;
    }

    public T remove(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        if (position == 0) {
            return removeAtHead();
        }
        if (position == size - 1) {
            return pop();
        }
        Node curr = head;
        for (int i = 0; i < position - 1; i++) {
            curr = curr.next;
        }
        T data = curr.next.data;
        curr.next = curr.next.next;
        size--;
        return data;
    }

    public boolean contains(T value) {
        Node curr = head;
        while (curr != null) {
            if (value == null ? curr.data == null : value.equals(curr.data)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public int positionOf(T value) {
        int pos = 0;
        Node curr = head;
        while (curr != null) {
            if (value == null ? curr.data == null : value.equals(curr.data)) {
                return pos;
            }
            curr = curr.next;
            pos++;
        }
        return -1;
    }

    public int lastPositionOf(T value) {
        int pos = 0;
        int last = -1;
        Node curr = head;
        while (curr != null) {
            if (value == null ? curr.data == null : value.equals(curr.data)) {
                last = pos;
            }
            curr = curr.next;
            pos++;
        }
        return last;
    }

    public T get(int position) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty!");
        }
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Position exceeds size of the list!");
        }
        Node curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    public T getAtHead() {
        return get(0);
    }

    public T getAtEnd() {
        return get(size - 1);
    }

    public void set(int position, T value) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty!");
        }
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Position exceeds size of the list!");
        }
        Node curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        curr.data = value;
    }

    public void setAtHead(T value) {
        set(0, value);
    }

    public void setAtEnd(T value) {
        set(size - 1, value);
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        Node curr = head;
        int i = 0;
        while (curr != null) {
            arr[i++] = curr.data;
            curr = curr.next;
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node curr = head;
        while (curr != null) {
            sb.append(String.valueOf(curr.data));
            if (curr.next != null) {
                sb.append(", ");
            }
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
