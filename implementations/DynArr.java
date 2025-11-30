import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class DynArr<T> {
    private T[] arr;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    DynArr() {
        capacity = 2;
        size = 0;
        arr = (T[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    DynArr(int initialCapacity) {
        capacity = initialCapacity;
        size = 0;
        arr = (T[]) new Object[capacity];
    }

    public DynArr(T[] array) {
        if (array == null)
            throw new NullPointerException("array is null");

        size = array.length;
        capacity = Math.max(2, Math.max(1, size) * 2);
        arr = Arrays.copyOf(array, this.capacity);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        T[] newArr = (T[]) new Object[capacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    @SuppressWarnings("unchecked")
    private void shrink() {
        int newCap = Math.max(1, capacity / 2);
        if (newCap < size)
            newCap = size;
        if (newCap == capacity)
            return;

        T[] newArr = (T[]) new Object[newCap];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
        capacity = newCap;
    }

    @SuppressWarnings("unchecked")
    public void shrinkToFit() {
        int newCap = Math.max(1, size);
        if (newCap == capacity)
            return;

        T[] newArr = (T[]) new Object[newCap];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
        capacity = newCap;
    }

    public void push(T value) {
        if (size == capacity)
            resize();
        arr[size++] = value;
    }

    public void push(T value, int index) {
        if (index >= size) {
            push(value);
            return;
        }

        if (index < 0)
            throw new IndexOutOfBoundsException("Cannot push at negative index!");
        
        if (size == capacity)
            resize();
        
        System.arraycopy(arr, index, arr, index + 1, size - index);

        arr[index] = value;

        size++;
    }

    public T pop() {
        if (size == 0)
            throw new IllegalStateException("Empty array!");

        T value = arr[size - 1];
        arr[size - 1] = null;
        size--;

        if (size <= capacity / 4)
            shrink();

        return value;
    }

    public T pop(int index) {
        if (size == 0)
            throw new IllegalStateException("Empty array!");

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        
        if (index == size - 1)
            return pop();

        T value = arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        arr[--size] = null;

        if (size <= capacity / 4)
            shrink();
        
        return value;
    }

    public void remove(T value) {
        int index = indexOf(value);

        if (index == -1) {
            throw new IllegalStateException("Element not found: " + value);
        }

        pop(index);
    }

    public void sort() {
        Arrays.sort(arr, 0, size);
    }

    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> cmp) {
        Arrays.sort(arr, 0, size, cmp);
    }

    public void reverse() {
        int left = 0;
        int right = size - 1;
        T temp;

        while (left < right) {
            temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        arr = (T[]) new Object[capacity];
        size = 0;
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[i], value))
                return i;
        }

        return -1;
    }

    public int indexOf(T value, int start, int end) {
        if (start < 0 || end < 0 || start > size || end > size)
            throw new IndexOutOfBoundsException("Invalid range: start=" + start + ", end=" + end);
        
        if (start > end) {
            throw new IllegalArgumentException("start cannot be greater than end: " + start + " > " + end);
        }
        
        for (int i = start; i < end; i++) {
            if (Objects.equals(arr[i], value)) {
                return i;
            }
        }

        return -1;
    }

    public int count(T value) {
        int ctr = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[i], value))
                ctr++;
        }

        return ctr;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        return arr[index];
    }

    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        arr[index] = value;
    }

    public int length() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] newArr = (T[]) new Object[size];
        System.arraycopy(arr, 0, newArr, 0, size);

        return newArr;
    }
}
